package can.i.has.grex.experiments.config

import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.runner.ExperimentRunner

interface ConfigProvider<R extends Result> {
    void begin()
    boolean hasNext()
    Config getNext(ExperimentRunner<R> runner)
    void end()
    void reset()
}
