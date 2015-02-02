package can.i.has.experiments.config

import can.i.has.experiments.Result
import can.i.has.utils.NamedList

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class FullSearchYield<R extends Result> implements ConfigYield<R> {
    List<String> names
    Map<String, List> domains
    Map<String, Map<String, Object>> values = [:]

    protected boolean fresh = true

    FullSearchYield(List<String> names, Map<String, List> domains, Map<String, Map<String, Object>> values) {
        this.names = names
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
        names.collect {domains[it]}.eachCombination { combination ->
            def key = combination.collect { "$it" }.join(".")
            def config = new NamedList(names, names.collect { "" })
            names.eachWithIndex { String entry, int i ->
                def val = combination[i]
                if (values.containsKey(entry))
                    val = values[entry][val]
                config[entry] = val
            }
            out[key] = config
        }
        out
    }

    @Override
    void retrieveResults(Map<NamedList, R> results) {
        // pass out gracefully after drinking too much
    }
}
