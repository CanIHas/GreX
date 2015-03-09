package can.i.has.grex

import can.i.has.grex.experiments.Experiment
import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.storage.DirBasedResultStorage
import can.i.has.grex.experiments.storage.ResultsStorage
import can.i.has.grex.experiments.storage.serialization.FSTSerializer
import can.i.has.grex.experiments.storage.serialization.ResultSerializer

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

//    void setRoot(def root){
//        assert root instanceof String || root instanceof File
//        def r = root
//        if (r instanceof String)
//            r = new File(r)
//        this.root = r
//    }

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
            out.text = ""
        }
        out
    }

//    void compile(String... path){
//        LaTeXCompilerUtils.compile(renderFile(path), buildFile(path.reverse().tail().reverse()))
//    }
//
//    static String[] dirPath(String... path){
//        path[0..-2]
//    }
//
//    static String[] relativePath(File root, File file){
//        root.toURI().relativize( file.toURI() ).toString().split(File.separator)
//    }

//    void compileAll(){
//        renderDir.eachFileRecurse { File f ->
//            if (f.file)
//                LaTeXCompilerUtils.compile(f,
//                    buildFile(
//                        dirPath(
//                            relativePath(
//                                renderDir,
//                                f
//                            )
//                        )
//                    )
//                )
//        }
//    }
//
//    public <T> T using(Closure<T> closure){
//        Manager.instance.withWorkspace(this, closure)
//    }

//    @Singleton
//    static class Manager {
//
//        static protected ThreadLocal<Workspace> activeWorkspace
//
//        static Workspace getActiveWorkspace() {
//            def out = activeWorkspace.get()
//            assert out != null        // todo: dedicated exception
//            out
//        }
//
//        static {
//            activeWorkspace = new ThreadLocal<>()
//            activeWorkspace.set(null)
//        }
//
//        public <T> T withWorkspace(Workspace workspace, Closure<T> closure) {
//            Workspace oldWorkspace = activeWorkspace.get()
//            try {
//                activeWorkspace.set(workspace)
//                def toCall = closure.clone()
//                toCall.delegate = activeWorkspace.get()
//                toCall.resolveStrategy = Closure.DELEGATE_FIRST
//                return toCall()
//            } finally {
//                activeWorkspace.set(oldWorkspace)
//            }
//        }
//    }
}

import can.i.has.grex.latex.model.Document

import groovy.transform.Canonical