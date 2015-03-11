package can.i.has.grex.experiments.new_model.config

import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.runner.ExperimentRunner
import can.i.has.utils.IterUtils
import can.i.has.utils.OrderedMap

import groovy.transform.Canonical

@Canonical
class FullSearchProvider<R extends Result> extends AbstractConfigProvider<R> {
    List<String> params
    Map<String, List> domains
    Map<String, Map<String, Object>> values = [:]

    @Override
    protected AbstractConfigProvider.ProviderRun<R> getNewRun() {
        new AbstractConfigProvider.ProviderRun<R>() {
            Iterator<OrderedMap> symbolicConfigIterator

            @Override
            void begin() {
                use (IterUtils) {
//                    symbolicConfigIterator = params.combinationsIterator(params.collect {
//                        [it, domains[it]]
//                    }.asMap())
                    symbolicConfigIterator = params.combinationsIterator(domains)
                }
            }

            @Override
            void end() {
                assert !symbolicConfigIterator.hasNext()
            }

            @Override
            boolean hasNext() {
                symbolicConfigIterator.hasNext()
            }

            @Override
            Config getNext(ExperimentRunner<R> runner) {
                def symbolicConfig = symbolicConfigIterator.next()
                def usableConfig = new OrderedMap(symbolicConfig.@order, [:])
                symbolicConfig.each { String paramKey, Object paramValueName ->
                    if (values.containsKey(paramKey)) {
                        assert values[paramKey].containsKey(paramValueName)
                        def val = values[paramKey][paramValueName]
                        if (val instanceof Closure)
                            val = val.call(paramValueName)
                        usableConfig[paramKey] = val
                    }
                    else {
                        usableConfig[paramKey] = paramValueName
                    }
                }
                new Config(symbolicConfig, usableConfig)
            }
        }
    }
}
