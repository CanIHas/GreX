package can.i.has.latex.model

import groovy.transform.Canonical

import static can.i.has.latex.Commands.*

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
            begin(name, beginArgs.toArray() as Renderable[])
        ] + content + [
            end(name, endArgs.toArray() as Renderable[])
        ]).collect {it.render()}.join("""
""")

    }
}
