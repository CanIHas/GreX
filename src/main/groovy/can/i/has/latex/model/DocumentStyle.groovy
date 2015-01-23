package can.i.has.latex.model

import groovy.transform.Canonical

@Canonical
class DocumentStyle implements LaTeXRenderable{
    //todo: use enums
    String clazz = "article"
    List<String> options = [ "a4paper","12pt" ]

    @Override
    String render() {
        "[${options.join(",")}]{${clazz}}"
    }
}
