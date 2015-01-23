package can.i.has.latex.experiments

import groovy.io.FileType
import groovy.transform.Canonical

@Canonical
class ExperimentMemory<R extends Result>{
    String name
    Workspace workspace

    protected @Lazy File dataDir = workspace.resultFile(name)

    boolean exists(String key){
        new File(dataDir, key+".obj").exists()
    }

    R getAt(String key){
        new File(dataDir, key+".obj").withObjectInputStream(this.class.classLoader) { ObjectInputStream ois ->
            (R) ois.readObject()
        }
    }

    void putAt(String key, R result){
        new File(dataDir, key+".obj").withObjectOutputStream { ObjectOutputStream oos ->
            oos.writeObject(result)
        }
    }

    /**
     *
     * @param closure (R)
     */
    void eachResult(Closure<Void> closure) {
        dataDir.eachFile(FileType.FILES) { File f ->
            closure(f.withObjectInputStream(this.class.classLoader) { (R) it.readObject() } )
        }
    }
}
