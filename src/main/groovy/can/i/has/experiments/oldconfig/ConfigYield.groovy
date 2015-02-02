package can.i.has.experiments.oldconfig

import can.i.has.experiments.Result
import can.i.has.utils.NamedList

import java.util.concurrent.Future

interface ConfigYield<R extends Result> {
    // contract
    void loadNext()
    NamedList getCurrent()
    String getCurrentKey()
    abstract void putFuture(Future<R> future)
//    void addResult(R result)
    void join()
    void close()

    // syntax sugar
    void leftShift(Future<R> future)//{
//        putFuture(future)
//    }
}
