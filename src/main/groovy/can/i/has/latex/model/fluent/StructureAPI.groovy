package can.i.has.latex.model.fluent

import can.i.has.latex.model.structure.Command
import can.i.has.latex.model.structure.Environment
import can.i.has.latex.model.Renderable
import can.i.has.latex.model.contents.StringRenderable


class StructureAPI {


    static Command command(String name, List<String> options=[], List<Renderable> args=[]){
        new Command(name, options, args)
    }

    static Command command(String name, String arg, String... options){
        new Command(name, options.toList(), [text(arg)])
    }

    static Environment env(String name, Renderable... content){
        new Environment(name, content.toList())
    }

    static Environment env(String name, List<Renderable> content){
        new Environment(name, content)
    }

    static Environment documentEnv(){
        new Environment("document", [])
    }
}
