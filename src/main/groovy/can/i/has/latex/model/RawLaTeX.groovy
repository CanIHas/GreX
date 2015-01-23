package can.i.has.latex.model

import groovy.transform.Canonical

@Canonical
class RawLaTeX implements DocumentElement{
    String content

    String render(){
        content
    }
}
