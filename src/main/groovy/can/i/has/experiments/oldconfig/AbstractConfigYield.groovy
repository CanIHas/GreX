package can.i.has.experiments.oldconfig

import can.i.has.experiments.Result
import can.i.has.utils.NamedList

import java.util.concurrent.Future

abstract class AbstractConfigYield<R extends Result> implements ConfigYield<R>{
    NamedList current = null
    protected List<Future<R>> futures = [].asSynchronized()
    protected Map<String, R> results = [:].asSynchronized()

    protected abstract NamedList getNext()
    protected abstract String toKey(NamedList config)

    void loadNext(){
        cleanUpFutures()
        current = getNext()
    }
    String getCurrentKey(){
        toKey(current)
    }
    void putFuture(Future<R> future){
        futures << future
    }

    void leftShift(Future<R> future){
        putFuture(future)
    }

    void cleanUpFutures(){
        def idx = 0
        while (idx<futures.size()) {
            if (futures[idx].done) {
                assert !futures[idx].cancelled
                def result = futures[idx].get()
                if (result!=null) {
                    results[result.key] = result
                    futures.remove(idx)
                }
            } else idx++
        }
    }

    void join(){
        futures.each {
            def result = it.get()
            results[result.key] = result
        }
        futures.clear()
    }

    void close(){
        join()
        results.clear()
    }
}
