package can.i.has.experiments.config

import can.i.has.experiments.Result
import can.i.has.utils.NamedList


interface ConfigYield<R extends Result> extends Iterator<ConfigBatch>{
    void retrieveResults(Map<NamedList, R> results)
}