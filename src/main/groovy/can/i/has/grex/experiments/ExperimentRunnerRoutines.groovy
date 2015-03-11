package can.i.has.grex.experiments

import can.i.has.grex.experiments.condition.Condition
import can.i.has.grex.experiments.old_model.config.ConfigYield
import can.i.has.grex.experiments.storage.ResultsStorage
import can.i.has.utils.OrderedMap

import groovy.transform.Canonical
import groovy.util.logging.Slf4j

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Closure by default should take two arguments: String key, NamedList  config.
 * It should be single run of experiment and it should return Result, describing this run.
 * This may mean, for example, running some evolutionary algorithm, gathering some statistics about last
 * population, copy of few chosen specimens, storing it in some implementation of Result and returning it.
 * Thanks to this we can use wrapping closures, for example for caching.
 */
@Slf4j
class ExperimentRunnerRoutines {
    static void skipIf(Object ignored, Condition condition, Runnable runnable){
        if (condition.satisfied) {
            log.info("Condition ${condition.toPrettyString()} not satisfied")
        } else {
            log.info("Condition ${condition.toPrettyString()} satisfied; skipping some actions")
            runnable()
        }
    }

    static void doIf(Object ignored, Condition condition, Runnable runnable){
        if (condition.satisfied) {
            log.info("Condition ${condition.toPrettyString()} not satisfied")
            runnable()
        } else {
            log.info("Condition ${condition.toPrettyString()} satisfied; skipping some actions")
        }
    }

    static <R extends Result> void eachConfig(ConfigYield configYield, Closure<R> closure){
        eachConfig(configYield, Executors.newSingleThreadExecutor(), closure)
    }


    static <R extends Result> void eachConfig(ConfigYield configYield, float coreFactor, Closure<R> closure){
        eachConfig(configYield, Math.max(Math.round(Runtime.runtime.availableProcessors()*coreFactor)), closure)
    }

    static <R extends Result> void eachConfig(ConfigYield configYield, int poolSize, Closure<R> closure){
        eachConfig(configYield, Executors.newFixedThreadPool(poolSize), closure)
    }

    static <R extends Result> void eachConfig(ConfigYield configYield, ExecutorService pool, Closure<R> closure){
        configYield.loadNext()
        def futures = []
        def watchlist = []
        while (configYield.current!=null) {
            def key = configYield.getCurrentKey()
            def config = configYield.current
            def future = pool.submit(new CallableImpl<R>(handleExceptions(watchlist, closure), [key, config]))
            futures << future
            configYield << future
            handleWatchlist(watchlist)
            configYield.loadNext()
        }
        configYield.close()
    }

    private static void handleWatchlist(List<Map> watchlist){
        if (watchlist) {
            watchlist.each {
                it.throwable.printStackTrace()
            }
            def summary = "" << "Summary exception:\n"
            summary<<"\tconfig / exception"
            watchlist.each { Map desc ->
                summary << "\t${desc.args[0]}\t${desc.throwable.toString()}\n"
            }
            summary << "\nSee stacktraces above for details"
            throw new RuntimeException(summary.toString())
        }
    }

    private static <R extends Result> Closure<R> handleExceptions(List<Map> watchlist, Closure<R> closure){
        return { List args ->
            try {
                def result = closure.call(*args)
                return result
            } catch (Throwable t) {
                watchlist << [throwable: t, args: args]
                return null
            }
        }
    }

    static <R extends Result> Closure<R> using(Closure<R> closure, ResultsStorage<R> storage){
        return { String key, OrderedMap config ->
            if (!storage.contains(key))
                storage << closure.call(key, config)
            return storage[key]

        }
    }

    @Canonical
    static private class CallableImpl<R extends Result> implements Callable<R> {

        Closure<R> closure
        List args

        @Override
        R call() throws Exception {
            closure.call(*args)
        }
    }
}
