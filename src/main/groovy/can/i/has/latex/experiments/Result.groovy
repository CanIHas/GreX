package can.i.has.latex.experiments

import can.i.has.latex.utils.NamedList


interface Result extends Serializable{
    void setConfig(NamedList list)
    NamedList getConfig()
}