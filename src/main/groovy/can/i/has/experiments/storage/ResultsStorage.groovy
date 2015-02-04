package can.i.has.experiments.storage

import can.i.has.experiments.Result
import can.i.has.utils.OrderedMap

trait ResultsStorage<R extends Result>{
    abstract boolean contains(OrderedMap<String> key)

    abstract R getAt(OrderedMap<String> key)

    abstract void putAt(OrderedMap<String> key, R result)

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
