package can.i.has.grex.experiments.storage.serialization

import can.i.has.grex.experiments.Result
import org.nustaq.serialization.FSTConfiguration

import groovy.transform.Canonical

/**
 * TODO: provide more serializers and publish them in different modules
 * @param < R >
 */
@Canonical
class FSTSerializer<R extends Result> implements ResultSerializer<R>{


    FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration()

    @Override
    byte[] serialize(R result) {
        conf.asByteArray(result)
    }

    @Override
    R deserialize(byte[] bytes) {
        (R)conf.asObject(bytes)
    }

    private static FSTSerializer<Result> defaultInstance=null;
    public static ResultSerializer<Result> getDefault(){
        if (defaultInstance==null)
            defaultInstance = new FSTSerializer<Result>();
        defaultInstance
    }
}
