package can.i.has.experiments

import can.i.has.utils.NamedList

import groovy.transform.Canonical

@Canonical
class ExperimentRunner<R extends Result> {
    String name
    KeyProvider keyProvider
    ConfigYield configYield
    Workspace workspace

    protected ExperimentMemory<R> experimentMemory

    /**
     *
     * @param experiment (NamedList)
     */
    void start(Closure<R> experiment){
        experimentMemory = new ExperimentMemory<>(name, workspace)
        configYield.eachConfig { NamedList config ->
            String key = keyProvider.generateKey(config)
            if (!experimentMemory.exists(key)){
                R result = experiment(config)
                result.setConfig(config)
                experimentMemory[key] = result
            }
        }
    }
}
