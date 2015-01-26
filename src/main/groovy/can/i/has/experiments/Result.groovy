package can.i.has.experiments

import can.i.has.utils.NamedList


interface Result extends Serializable{
    void setConfig(NamedList list)
    NamedList getConfig()
}