package can.i.has.latex.model

import groovy.transform.Canonical

@Canonical
class StringRenderable implements Renderable{
    final String text

    @Override
    String render() {
        text
    }
}
