package can.i.has.experiments

import can.i.has.utils.NamedList


interface KeyProvider {
    String generateKey(NamedList config)
}
