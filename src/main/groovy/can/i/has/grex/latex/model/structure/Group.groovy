package can.i.has.grex.latex.model.structure

import can.i.has.grex.Workspace
import can.i.has.grex.latex.model.Renderable
import can.i.has.utils.WithContent

import groovy.transform.Canonical

@Canonical
class Group implements Renderable, WithContent<List<Renderable>> {
    List<Renderable> content = []

    @Override
    String render(Workspace workspace) {
        content.collect {it.render(workspace)}.join("\n")
    }

    @Override
    Set<String> getNeededPackages() {
        content.collect { it.neededPackages }.flatten().toSet()
    }
}
