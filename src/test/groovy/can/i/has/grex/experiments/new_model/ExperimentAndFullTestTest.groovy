package can.i.has.grex.experiments.new_model

import can.i.has.grex.TestConstants
import can.i.has.grex.experiments.Experiment
import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.config.Config
import can.i.has.grex.experiments.storage.InMemoryStorage
import can.i.has.grex.experiments.storage.ResultsStorage
import can.i.has.utils.OrderedMap

class ExperimentAndFullTestTest extends GroovyTestCase {
    Experiment experiment
    ResultsStorage<ResultMock> storage = new InMemoryStorage<>("storageMock", [:])
    List<OrderedMap<String>> expectedKeys = [
        new OrderedMap<String>(["a", "b", "c"], [a: "1", b: "3", c: "x"]),
        new OrderedMap<String>(["a", "b", "c"], [a: "1", b: "3", c: "y"]),
        new OrderedMap<String>(["a", "b", "c"], [a: "1", b: "4", c: "x"]),
        new OrderedMap<String>(["a", "b", "c"], [a: "1", b: "4", c: "y"]),
        new OrderedMap<String>(["a", "b", "c"], [a: "2", b: "3", c: "x"]),
        new OrderedMap<String>(["a", "b", "c"], [a: "2", b: "3", c: "y"]),
        new OrderedMap<String>(["a", "b", "c"], [a: "2", b: "4", c: "x"]),
        new OrderedMap<String>(["a", "b", "c"], [a: "2", b: "4", c: "y"])
    ]


    class ResultMock implements Result {
        static int instanceCount = 0
        int number

        ResultMock(OrderedMap<String> key) {
            this.key = key
            number = instanceCount++
        }
        OrderedMap<String> key
    }

    void setUp(){
        experiment = TestConstants.getExampleExperiment { Config config ->
            return new ResultMock(config.symbolicConfig)
        }
    }

    void testExperimentWithFullTest(){
        //todo: test breaking experiment and re-running it
        experiment.perform(storage)
        def results = []
        storage.eachResult {
            results << it
        }
        assert results.collect { it.number }.sort() == (0..7)
        assert results.sort { it.number }.collect { it.key } == expectedKeys
        //todo: test storage after running experiment
    }
}
