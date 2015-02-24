package can.i.has.latex.model.structure

import can.i.has.latex.model.Renderable
import can.i.has.latex.model.fluent.ContentCommands

import groovy.transform.Canonical

@Canonical
class Environment extends Group{
    String name
    List<Renderable> beginArgs
    List<Renderable> endArgs

    Environment(String name, List<Renderable> beginArgs = [], List<Renderable> endArgs = [], List<Renderable> content) {
        super(content)
        this.name = name
        this.beginArgs = beginArgs
        this.endArgs = endArgs
    }

    Environment(HashMap args) {
        super(args)
        this.name = args?.name
    }

    @Override
    String render() {
        ([
            ContentCommands.begin(name, beginArgs.toArray() as Renderable[])
        ] + content + [
            ContentCommands.end(name, endArgs.toArray() as Renderable[])
        ]).collect {it.render()}.join("""
""")
    }

}
