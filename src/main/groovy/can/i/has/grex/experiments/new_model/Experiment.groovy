package can.i.has.grex.experiments.new_model

import can.i.has.grex.Workspace
import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.new_model.config.Config
import can.i.has.grex.experiments.new_model.config.ConfigProvider
import can.i.has.grex.experiments.runner.ExperimentRunner
import can.i.has.grex.experiments.storage.ResultsStorage

import groovy.transform.Canonical

@Canonical
class Experiment<R extends Result>{
    String name
    Closure<R> experimentBody
    ConfigProvider<R> configProvider
    ExperimentRunner<R> experimentRunner

    void setConfig(ConfigProvider<R> configProvider){
        this.configProvider = configProvider
    }

    void setRunner(ExperimentRunner<R> experimentRunner){
        this.experimentRunner = experimentRunner
    }

    void perform(ResultsStorage<R> storage){
        configProvider.begin()
        while (configProvider.hasNext()) {
            Config config = configProvider.getNext(experimentRunner)
            experimentRunner.runTaskWithStorage(storage, config.symbolicConfig, { experimentBody.call(config) })
        }
        configProvider.end()
        configProvider.reset()

    }

    void perform(Workspace workspace){
        perform(workspace.newStorage(name))
    }
}
