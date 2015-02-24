package can.i.has.latex.model.contents

import can.i.has.latex.model.Renderable

import groovy.transform.Canonical

@Canonical
class StringRenderable implements Renderable{
    final String text

    @Override
    String render() {
        text
    }

    @Override
    Set<String> getNeededPackages() {
        [].toSet()
    }
}
