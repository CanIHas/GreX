package can.i.has.latex.model

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
