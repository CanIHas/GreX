package can.i.has.experiments.config

import can.i.has.experiments.Result
import can.i.has.utils.IterUtils
import can.i.has.utils.OrderedMap

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static can.i.has.utils.IterUtils.asOrderedMap

@EqualsAndHashCode
@ToString
class FullSearchYield<R extends Result> implements ConfigYield<R> {
    List<String> params
    Map<String, List> domains
    Map<String, Map<String, Object>> values = [:]

    protected boolean fresh = true

    FullSearchYield(List<String> params, Map<String, List> domains, Map<String, Map<String, Object>> values) {
        this.params = params
        this.domains = domains
        this.values = values
        values.each { String param, Map<String, Object> vals ->
            if (!domains.containsKey(param))
                domains.put(param, vals.keySet().toList().sort())
        }
    }

    @Override
    boolean hasNext() {
        fresh
    }

    @Override
    ConfigBatch next() {
        fresh = false
        def out = new SimpleConfigBatch([:])
        params.collect {domains[it]}.eachCombination { combination ->
            def key = asOrderedMap(params, [:])
            def config = new OrderedMap(params, [:])
            params.eachWithIndex { String entry, int i ->
                def val = combination[i]
                key[entry] = val
                if (values.containsKey(entry))
                    val = values[entry][val]
                config[entry] = val
            }
            out[key] = config
        }
        out
    }

    @Override
    void remove() {
        //todo: refactor ConfigYield, it should be Iterable, not Iterator; also ConfigBatch needs ordering
    }

    @Override
    void retrieveResults(Map<OrderedMap, R> results) {
        // pass out gracefully after drinking too much
    }
}
