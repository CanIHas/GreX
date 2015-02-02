package can.i.has.experiments.runner

import can.i.has.experiments.Result

import java.util.concurrent.Callable


class SingleThreadExperimentRunner<R extends Result> implements ExperimentRunner<R> {
    @Override
    void runWithCallback(String key, Callable<R> callable, Closure<Void> callback) {
        callback.call(callable.call())
    }

    @Override
    void join() {

    }

    @Override
    void close() {

    }
}
