package can.i.has.experiments

import can.i.has.utils.OrderedMap


interface Result extends Serializable{
    void setKey(OrderedMap<String> key)
    OrderedMap<String> getKey()
//    void setConfig(OrderedMap list)
//    OrderedMap getConfig()
}