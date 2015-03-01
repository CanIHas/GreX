package can.i.has.grex.experiments.storage

import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.storage.serialization.FSTSerializer
import can.i.has.grex.experiments.storage.serialization.ResultSerializer
import can.i.has.utils.OrderedMap

import groovy.io.FileType
import groovy.transform.Canonical

@Canonical
class DirBasedResultStorage<R extends Result> implements ResultsStorage<R>{
    String name
    File dataDir
    ResultSerializer<R> serializer = FSTSerializer.default

    boolean contains(OrderedMap<String> key){
        file(key).exists()
    }

    protected String filename(OrderedMap<String> key){
        key.valuesList().join(".")
    }

    protected File file(OrderedMap<String> key){
        new File(dataDir, filename(key)+".obj")
    }

    R getAt(OrderedMap<String> key){
        serializer.deserialize(file(key).bytes)
//            withObjectInputStream(this.class.classLoader) { ObjectInputStream ois ->
//            (R) ois.readObject()
//        }

    }

    void putAt(OrderedMap<String> key, R result){
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
