package can.i.has.grex.latex.model.sections

import can.i.has.grex.Workspace
import can.i.has.grex.latex.model.structure.Group

import groovy.transform.Canonical

@Canonical
class DocumentSection extends Group {
    SectionLevel level
    String title
    boolean ignored = false

    @Override
    String render(Workspace workspace) {
        [
            "\\$level${ignored?'*':''}{$title}",
            super.render(workspace)
        ].join("\n")
    }

    @Override
    Set<String> getNeededPackages() {
        content.collect { it.neededPackages }.flatten()
    }

}
