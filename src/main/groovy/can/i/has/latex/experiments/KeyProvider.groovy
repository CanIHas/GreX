package can.i.has.latex.experiments

import can.i.has.latex.utils.NamedList


interface KeyProvider {
    String generateKey(NamedList config)
}
