package can.i.has.grex.experiments.old_model

import can.i.has.grex.Workspace
import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.old_model.config.ConfigBatch
import can.i.has.grex.experiments.old_model.config.ConfigYield
import can.i.has.grex.experiments.runner.ExperimentRunner
import can.i.has.grex.experiments.storage.ResultsStorage
import can.i.has.utils.OrderedMap

import groovy.transform.Canonical
import groovy.util.logging.Slf4j

import java.util.concurrent.Callable

@Canonical
@Slf4j
class Experiment<R extends Result> {
    String name
    ConfigYield<R> configYield
    @Lazy ResultsStorage<R> resultsStorage = Workspace.Manager.activeWorkspace.newStorage(name)

    /**
     *
     * @param experimentRunner
     * @param experimentBody (String key, OrderedMap config)
     * @return this
     */
    Experiment<R> runWith(ExperimentRunner<R> experimentRunner, Callable<R> experimentBody){
        configYield.each { ConfigBatch batch ->
            batch.each { OrderedMap key, OrderedMap config ->
                experimentRunner.runTaskWithStorage(key, {
                    def out
                    if (resultsStorage.contains(key)) {
                        out = resultsStorage[key]
                        log.debug("Retrieved from memory: $out")
                    }
                    else {
                        out = experimentBody.call(key, config)
                        out.key = key
//                        out.config = config
                        log.debug("Experiment result: $out")
                    }
                    return out
                }, { R result ->
                    if (!resultsStorage.contains(key))
                        resultsStorage.putAt(key, result)
                    System.gc()
                })
            }
            experimentRunner.join()
        }
        experimentRunner.close()
        return this
    }

    static final Map<String, Closure> propertyExtractors = [:].withDefault { String propName ->
        return { R result ->
            return result."$propName"
        }
    }

//    ColumnTable table(List<String> valueColNames, Map<String, Closure> extractors){
//        def params = configYield.params
//        def header = params + valueColNames
//        def data = []
//        resultsStorage.eachResult { R result ->
//            data << params.collect { result.key[it] } + valueColNames.collect {
//                extractors[it].call(result)
//            }
//        }
//        return new ColumnTable(header, data)
//    }
}
