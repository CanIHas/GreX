package can.i.has.latex.model

import groovy.transform.Canonical

@Canonical
class Group implements Renderable{
    List<Renderable> content

    @Override
    String render() {
        "{"+content.collect {it.render()}.join("""
""")+"}"
    }
}
