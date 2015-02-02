package can.i.has.experiments

import can.i.has.experiments.config.ConfigBatch
import can.i.has.experiments.config.ConfigYield
import can.i.has.experiments.runner.ExperimentRunner
import can.i.has.experiments.storage.ResultsStorage
import can.i.has.utils.NamedList

import groovy.transform.Canonical

import java.util.concurrent.Callable

@Canonical
class Experiment<R extends Result> {
    String name
    ResultsStorage<R> resultsStorage
    ConfigYield<R> configYield

    void runWith(ExperimentRunner<R> experimentRunner, Callable<R> experimentBody){
        configYield.each { ConfigBatch batch ->
            batch.each { String key, NamedList config ->
                experimentRunner.runWithCallback(key, {
                    def out = resultsStorage.contains(key) ?
                        resultsStorage[key] :
                        experimentBody.call(config)
                    out.key = key
                    out.config = config
                    return out
                }, { R result ->
                    resultsStorage.putAt(key, result)
                    System.gc()
                })
            }
            experimentRunner.join()
        }
        experimentRunner.close()
    }
}
