package can.i.has.experiments.oldconfig

import can.i.has.experiments.Result
import can.i.has.utils.NamedList

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class FullSearchYield<R extends Result> extends AbstractConfigYield<R>{
    List<String> names
    Map<String, List> domains
    Map<String, Map<String, Object>> values = [:]
    //todo: constructor should fill domains with values
    protected Map<String, Object> currentKeys = null
    protected Map<String, Iterator> iterators = null

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
    NamedList getNext() {
        if (iterators==null){
            currentKeys = [:]
            iterators = [:]
            names.each { String parameter ->
                iterators[parameter] = domains[parameter].iterator()
                if (parameter!=names.last())
                    incr(parameter)
            }
        }
        try {
            incr(names.last())
            def out = buildConfig()
            return out
        } catch (RuntimeException exc) { //todo:specialize exception
            exc.printStackTrace()
            return null
        }

    }

    protected void incr(String parameter){
        if (!iterators[parameter].hasNext()) {
            iterators[parameter] = domains[parameter].iterator()
            def idx = names.indexOf(parameter)
            if (idx>0)
                incr(names[idx-1])
            else
                throw new RuntimeException("Recursion breaker")
        }
        currentKeys[parameter] = iterators[parameter].next()
    }

    protected NamedList buildConfig(){
        def out = new NamedList(names, names.collect {
            if (values.containsKey(it)) {
                assert "Unresolvable parameter $it value ${currentKeys[it]}" && values[it].containsKey(currentKeys[it]) //todo: specialize exception
                return values[it][currentKeys[it]]
            }
            currentKeys[it]
        })
        out
    }

    @Override
    String toKey(NamedList config) {
        names.collect { "${currentKeys[it]}" }.join(".")
    }
}
