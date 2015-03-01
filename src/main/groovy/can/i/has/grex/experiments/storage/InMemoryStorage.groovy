package can.i.has.grex.experiments.storage

import can.i.has.grex.experiments.Result
import can.i.has.utils.OrderedMap

import groovy.transform.Canonical

@Canonical
class InMemoryStorage<R extends Result> implements ResultsStorage<R>{
    Map<OrderedMap<String>, R> delegate

    @Override
    boolean contains(OrderedMap<String> key) {
        delegate.containsKey(key)
    }

    @Override
    R getAt(OrderedMap<String> key) {
        delegate[key]
    }

    @Override
    void putAt(OrderedMap<String> key, R result) {
        delegate[key] = result
    }

    @Override
    void eachResult(Closure<Void> closure) {
        delegate.values().each(closure)
    }
}
