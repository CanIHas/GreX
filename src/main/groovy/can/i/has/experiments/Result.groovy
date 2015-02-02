package can.i.has.experiments

import can.i.has.utils.NamedList


trait Result implements Serializable{
    abstract void setKey(String key)
    abstract String getKey()
    abstract void setConfig(NamedList list)
    abstract NamedList getConfig()
}