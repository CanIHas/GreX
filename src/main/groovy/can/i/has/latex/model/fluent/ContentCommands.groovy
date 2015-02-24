package can.i.has.latex.model.fluent

import can.i.has.experiments.Workspace
import can.i.has.latex.model.contents.FileRenderable
import can.i.has.latex.model.structure.Command
import can.i.has.latex.model.Renderable
import can.i.has.latex.model.contents.StringRenderable


class ContentCommands {



    static Command begin(String text, Renderable... renderables){
        new Command("begin", [], [new StringRenderable(text)]+renderables.toList())
    }

    static Command end(String text, Renderable... renderables){
        new Command("end", [], [new StringRenderable(text)]+renderables.toList())
    }

    static Command newline(){
        new Command("\\")
    }

    static Command emph(String text){
        new Command("emph", [], [new StringRenderable(text)])
    }

    static Command tableOfContents(){
        new Command("tableofcontents")
    }

    static Command title(){
        new Command("maketitle")
    }

    static StringRenderable text(String txt){
        new StringRenderable(txt)
    }

    static raw(String... path){
        new FileRenderable(Workspace.Manager.activeWorkspace.rawFile(path))
    }

}
