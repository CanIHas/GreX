package can.i.has.grex.latex.model.contents

import can.i.has.grex.Workspace
import can.i.has.grex.latex.model.Renderable

import groovy.transform.Canonical

@Canonical
class StringRenderable implements Renderable{
    String text

    @Override
    String render(Workspace workspace) {
        text
    }

    @Override
    Set<String> getNeededPackages() {
        [].toSet()
    }
}
