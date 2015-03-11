package can.i.has.grex.experiments.new_model.config

import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.runner.ExperimentRunner
import can.i.has.utils.OrderedMap


interface ConfigProvider<R extends Result> {
    void begin()
    boolean hasNext()
    Config getNext(ExperimentRunner<R> runner)
    void end()
    void reset()
}
