package can.i.has.latex.model.builder

import can.i.has.latex.experiments.Workspace
import can.i.has.latex.model.Document
import can.i.has.latex.model.DocumentStyle
import can.i.has.latex.model.RawLaTeX
import can.i.has.latex.model.RawLaTeXFile


@Singleton
class Builder {
    Document document(Map props, Closure closure){
        DocumentBuilder.instance.build(props, closure)
    }

    DocumentStyle style(Map props, Closure closure){
        documentStyle(props, closure)
    }

    DocumentStyle documentStyle(props, closure){
        DocumentStyleBuilder.instance.build(props, closure)
    }

    File file(String... path){
        Workspace.resolve(new File(), path)
    }

    RawLaTeX raw(String txt){
        new RawLaTeX(txt)
    }

    RawLaTeXFile rawFile(File file){
        new RawLaTeXFile(file)
    }

    RawLaTeXFile rawFile(String... rawFile){
        Workspace.Manager.activeWorkspace.rawFile(rawFile)
    }
}
