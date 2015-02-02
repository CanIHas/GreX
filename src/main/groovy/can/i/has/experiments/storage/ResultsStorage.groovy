package can.i.has.experiments.storage

import can.i.has.experiments.Result

trait ResultsStorage<R extends Result>{
    abstract boolean contains(String key)

    abstract R getAt(String key)

    abstract void putAt(String key, R result)

    /**
     *
     * @param closure (R)
     */
    abstract void eachResult(Closure<Void> closure)

    ResultsStorage<R> leftShift(R result){
        putAt(result.key, result)
        this
    }
}
