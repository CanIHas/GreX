package can.i.has.grex.experiments.runner

import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.storage.ResultsStorage
import can.i.has.utils.OrderedMap

import groovy.transform.Canonical

import java.util.concurrent.Callable

@Canonical
class SingleThreadExperimentRunner<R extends Result> extends AbstractExperimentRunner<R> {

    @Override
    R runTask(Callable<R> callable) {
        callable.call()
    }

    @Override
    void join() {

    }

}
