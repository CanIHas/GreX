package can.i.has.grex

import can.i.has.grex.experiments.old_model.Experiment
import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.storage.DirBasedResultStorage
import can.i.has.grex.experiments.storage.ResultsStorage
import can.i.has.grex.experiments.storage.serialization.FSTSerializer
import can.i.has.grex.experiments.storage.serialization.ResultSerializer
import can.i.has.grex.latex.model.Document

import groovy.transform.Canonical

@Canonical
class Workspace {
    File root
    List<Experiment> experiments = []
    List<Document> documents = []

    void setRoot(String root){
        this.root = new File(root)
    }

    void setRoot(File root){
        this.root = root
    }

    File getResultsDir(){
        new File(root, "results")
    }

    File getRenderDir(){
        new File(root, "render")
    }

    File getRawDir(){
        new File(root, "raw")
    }

    File getDslDir(){
        new File(root, "dsl")
    }

    File getBuildDir(){
        new File(root, "build")
    }

    File resultFile(String... path) {
        resolve(resultsDir, path)
    }

    File renderFile(String... path) {
        resolve(renderDir, path)
    }

    File rawFile(String... path) {
        resolve(rawDir, path)
    }

    File dslFile(String... path){
        resolve(dslDir, path)
    }

    File buildFile(String... path){
        resolve(buildDir, path)
    }

    public <R extends Result> ResultsStorage<R> newStorage(String name, ResultSerializer<R> serializer = new FSTSerializer<R>()){
        new DirBasedResultStorage<R>(name, resultFile(name), serializer)
    }

    static File resolve(File root, String[] path) {
        File out = root
        path.each {
            out = new File(out, it)
        }
        if (!out.exists()) {
            out.parentFile.mkdirs()
            out.createNewFile()
        }
        out
    }

    String toLatex(String name){
        def doc = documents.find { it.name == name }
        assert doc //todo: exception
        doc.render(this)
    }

    Map<String, String> allToLatex(){
        def out = [:]
        documents.each {
            out[it.name] = it.render(this)
        }
        out
    }

    void render(String name, String extension=".tex"){
        renderFile(name+extension).text = toLatex(name)
    }

    void renderAll(String extension=".tex"){
        allToLatex().each { String name, String latex ->
            renderFile(name).text = latex
        }
    }

    //todo: building

}