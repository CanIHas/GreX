package can.i.has.latex.model

import groovy.transform.Canonical

import static can.i.has.latex.Commands.*

@Canonical
class Environment extends Group{
    String name

    Environment(String name, List<Renderable> content) {
        super(content)
        this.name = name
    }

    Environment(HashMap args) {
        super(args)
        this.name = args?.name
    }

    @Override
    String render() {
        ([
            begin(name)
        ] + content + [
            end(name)
        ]).collect {it.render()}.join("""
""")

    }
}
