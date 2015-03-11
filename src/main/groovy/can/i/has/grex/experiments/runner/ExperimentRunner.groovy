package can.i.has.grex.experiments.runner

import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.storage.ResultsStorage
import can.i.has.utils.OrderedMap

import java.util.concurrent.Callable


interface ExperimentRunner<R extends Result> {
    Map<OrderedMap<String>, R> getAccumulatedResults()
    /**
     * Clean accumulatedResults
     */
    void purge()

    /**
     *
     * @param callable ()
     * @param callback (R result)
     */
    void runTaskWithStorage(ResultsStorage<R> resultsStorage, OrderedMap<String> key, Callable<R> callable)
    /**
     * Join all tasks enqueued until now
     */
    void join()

    void close()
}
