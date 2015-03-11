package can.i.has.grex.experiments.config

import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.runner.ExperimentRunner
import can.i.has.utils.IterUtils
import can.i.has.utils.OrderedMap

import groovy.transform.Canonical

@Canonical
class FullSearchProvider<R extends Result> extends AbstractConfigProvider<R> {
    List<String> params
    Map<String, List> domains
    /**
     * Map String -> (Closure or Map<String, Object>)
     * if value is Closure, it should have signature (String key) and should be called to get entity for key
     * else, if it is Map, symbolic param value should be used as key, and retrieved value should be used
     * as config entity.
     */
    Map<String, Object> values = [:]

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
                        assert values[paramKey] instanceof Closure || values[paramKey] instanceof Map
                        def val
                        if (values[paramKey] instanceof Closure)
                            val = values[paramKey].call(paramValueName)
                        else
                            val = values[paramKey][paramValueName]
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
