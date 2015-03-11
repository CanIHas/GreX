package can.i.has.grex.experiments.old_model.config

import can.i.has.grex.experiments.Result
import can.i.has.utils.OrderedMap


interface ConfigYield<R extends Result> extends Iterator<ConfigBatch>{
    List<String> getParams()
    void retrieveResults(Map<OrderedMap, R> results)
}