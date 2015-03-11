package can.i.has.grex.experiments.old_model.config

import can.i.has.utils.OrderedMap

import groovy.transform.Canonical

@Canonical
class SimpleConfigBatch implements ConfigBatch{
    @Delegate Map<OrderedMap<String>, OrderedMap> delegate
}
