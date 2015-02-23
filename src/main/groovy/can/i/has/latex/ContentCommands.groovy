package can.i.has.latex

import can.i.has.latex.model.Command
import can.i.has.latex.model.Renderable
import can.i.has.latex.model.StringRenderable


class ContentCommands {
    static Command documentClass(String style="article", List<String> options=[]){
        new Command("documentclass", options, [new StringRenderable(style)])
    }


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

}
