package can.i.has.latex

import can.i.has.latex.model.Environment
import can.i.has.latex.model.Renderable


class Groups {
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
