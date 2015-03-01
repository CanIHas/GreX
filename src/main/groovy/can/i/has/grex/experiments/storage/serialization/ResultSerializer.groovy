package can.i.has.grex.experiments.storage.serialization

import can.i.has.grex.experiments.Result


interface ResultSerializer<R extends Result> {
    byte[] serialize(R result)
    R deserialize(byte[] bytes)
}