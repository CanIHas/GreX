package can.i.has.experiments.storage

import can.i.has.experiments.Result

import groovy.transform.Canonical

@Canonical
class InMemoryStorage<R extends Result> implements ResultsStorage<R>{
    Map<String, R> delegate

    @Override
    boolean contains(String key) {
        delegate.containsKey(key)
    }

    @Override
    R getAt(String key) {
        delegate[key]
    }

    @Override
    void putAt(String key, R result) {
        delegate[key] = result
    }

    @Override
    void eachResult(Closure<Void> closure) {
        delegate.values().each(closure)
    }
}
