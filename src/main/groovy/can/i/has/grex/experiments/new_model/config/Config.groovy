package can.i.has.grex.experiments.new_model.config

import can.i.has.utils.OrderedMap

import groovy.transform.Canonical
import groovy.transform.Immutable

@Canonical
class Config {
    final OrderedMap<String> symbolicConfig
    final OrderedMap usableConfig
}
