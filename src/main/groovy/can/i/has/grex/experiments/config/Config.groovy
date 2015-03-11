package can.i.has.grex.experiments.config

import can.i.has.utils.OrderedMap

import groovy.transform.Canonical

@Canonical
class Config {
    final OrderedMap<String> symbolicConfig
    final OrderedMap usableConfig
}
