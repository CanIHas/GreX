package can.i.has.experiments.storage

import can.i.has.experiments.Result
import can.i.has.experiments.storage.serialization.FSTSerializer
import can.i.has.experiments.storage.serialization.ResultSerializer

import groovy.io.FileType
import groovy.json.JsonBuilder
import groovy.transform.Canonical

@Canonical
class DirBasedResultStorage<R extends Result> implements ResultsStorage<R>{
    String name
    File dataDir
    ResultSerializer<R> serializer = FSTSerializer.default

    boolean contains(String key){
        new File(dataDir, key+".obj").exists()
    }

    protected File file(String key){
        new File(dataDir, key+".obj")
    }

    R getAt(String key){
        serializer.deserialize(new File(dataDir, key+".obj").bytes)
//            withObjectInputStream(this.class.classLoader) { ObjectInputStream ois ->
//            (R) ois.readObject()
//        }

    }

    void putAt(String key, R result){
        def dest = file(key)
        if (!dest.exists()) {
            dataDir.mkdirs()
            dest.createNewFile()
        }
        dest.bytes = serializer.serialize(result)
//        dest.text = new JsonBuilder(this).toPrettyString()
//        dest.withObjectOutputStream { ObjectOutputStream oos ->
//            assert result instanceof Serializable
//            oos.writeObject(result)
//        }
    }

    /**
     *
     * @param closure (R)
     */
    void eachResult(Closure<Void> closure) {
        dataDir.eachFile(FileType.FILES) { File f ->
            closure(serializer.deserialize(f.bytes))
        }
    }
}
