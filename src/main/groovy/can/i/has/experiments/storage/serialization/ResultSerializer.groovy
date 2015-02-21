package can.i.has.experiments.storage.serialization

import can.i.has.experiments.Result


interface ResultSerializer<R extends Result> {
    byte[] serialize(R result)
    R deserialize(byte[] bytes)
}