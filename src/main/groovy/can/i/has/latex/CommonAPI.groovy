package can.i.has.latex

import can.i.has.latex.model.Command
import can.i.has.latex.model.Environment
import can.i.has.latex.model.Renderable
import can.i.has.latex.model.StringRenderable


class CommonAPI {
    static StringRenderable text(String txt){
        new StringRenderable(txt)
    }

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
