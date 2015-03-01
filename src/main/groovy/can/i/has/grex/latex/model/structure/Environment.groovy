package can.i.has.grex.latex.model.structure

import can.i.has.grex.Workspace
import can.i.has.grex.latex.model.Renderable

import groovy.transform.Canonical

@Canonical
class Environment extends Group{
    String name
    List<Renderable> beginArgs
    List<Renderable> endArgs

//    Environment(String name, List<Renderable> beginArgs = [], List<Renderable> endArgs = [], List<Renderable> content) {
//        super(content)
//        this.name = name
//        this.beginArgs = beginArgs
//        this.endArgs = endArgs
//    }
//
//    Environment(HashMap args) {
//        super(args)
//        this.name = args?.name
//    }

    static Command begin(String name, List<Renderable> args){
        new Command("begin", [], args)
    }

    static Command end(String name, List<Renderable> args){
        new Command("end", [], args)
    }

    @Override
    String render(Workspace workspace) {
        [
            begin(name, beginArgs).render(),
            super.render(),
            end(name, endArgs).render()
        ].join("\n")
    }

}
