package can.i.has.latex.model.contents

import can.i.has.latex.model.Renderable

import groovy.transform.Canonical

@Canonical
class FileRenderable implements Renderable{
    File file // assert it is not null

    @Override
    String render() {
        file.text
    }

    @Override
    Set<String> getNeededPackages() {
        return [].toSet()
    }
}
