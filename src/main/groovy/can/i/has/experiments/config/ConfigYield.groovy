package can.i.has.experiments.config

import can.i.has.experiments.Result
import can.i.has.utils.OrderedMap


interface ConfigYield<R extends Result> extends Iterator<ConfigBatch>{
    List<String> getParams()
    void retrieveResults(Map<OrderedMap, R> results)
}