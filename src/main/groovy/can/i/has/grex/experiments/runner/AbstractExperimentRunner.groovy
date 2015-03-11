package can.i.has.grex.experiments.runner

import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.storage.ResultsStorage
import can.i.has.utils.OrderedMap

import java.util.concurrent.Callable

abstract class AbstractExperimentRunner<R extends Result> implements ExperimentRunner<R> {
    final Map<OrderedMap<String>, R> accumulatedResults = [:]

    @Override
    void purge() {
        accumulatedResults.clear()
    }

    abstract R runTask(Callable<R> callable)

    @Override
    void runTaskWithStorage(ResultsStorage<R> resultStorage, OrderedMap<String> key, Callable<R> callable) {
        R result = runTask {
            if (!resultStorage.contains(key))
                resultStorage[key] = (R) callable.call()
            return resultStorage[key]
        }
        accumulatedResults[key] = result
    }


    @Override
    void close() {
        purge()
    }
}
