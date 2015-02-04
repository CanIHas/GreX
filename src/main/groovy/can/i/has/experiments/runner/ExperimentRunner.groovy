package can.i.has.experiments.runner

import can.i.has.experiments.Result
import can.i.has.utils.OrderedMap

import java.util.concurrent.Callable


interface ExperimentRunner<R extends Result> {
    /**
     *
     * @param callable ()
     * @param callback (R result)
     */
    void runWithCallback(OrderedMap<String> key, Callable<R> callable, Closure<Void> callback)
    /**
     * Join all tasks enqueued until now
     */
    void join()

    void close()
}
