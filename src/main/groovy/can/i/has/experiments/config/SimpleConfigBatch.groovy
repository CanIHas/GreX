package can.i.has.experiments.config

import can.i.has.utils.OrderedMap

import groovy.transform.Canonical

@Canonical
class SimpleConfigBatch implements ConfigBatch{
    @Delegate Map<OrderedMap<String>, OrderedMap> delegate
}
