package can.i.has.latex.model

import groovy.transform.Canonical

@Canonical
class Document extends LaTeXContainer{
    DocumentStyle style

    @Override
    String getHeader(){
        """\\documentclass${style.render()}
\\begin{document}"""
    }

    @Override
    String getHeaderContentSeparator(){
        "\n"
    }

    @Override
    String getContentFooterSeparator(){
        "\n"
    }

    @Override
    String getFooter(){
        "\\end{document}"
    }

}
