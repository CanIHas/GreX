package can.i.has.latex.model

import groovy.transform.Canonical

@Canonical
abstract class LaTeXContainer implements LaTeXRenderable{

    List<DocumentElement> elements = []

    String getHeader() { "" }
    String getFooter() { "" }
    String getHeaderContentSeparator() { "" }
    String getContentFooterSeparator() { "" }
    String getContentSeparator() { "\n" }

    @Override
    String render() {
        def out = "" << ""
        out << header << headerContentSeparator <<
            elements.collect { it.render() }.join(contentSeparator) << contentFooterSeparator <<
            footer
        out.toString()
    }
}
