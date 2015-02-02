package can.i.has.experiments.config

import can.i.has.utils.NamedList

import groovy.transform.Canonical

@Canonical
class SimpleConfigBatch implements ConfigBatch{
    @Delegate Map<String, NamedList> delegate
}
