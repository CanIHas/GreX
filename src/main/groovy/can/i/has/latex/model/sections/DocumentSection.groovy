package can.i.has.latex.model.sections

import can.i.has.latex.model.Renderable
import can.i.has.latex.model.fluent.StructureAPI
import can.i.has.latex.model.fluent.ContentCommands
import can.i.has.utils.DelegationUtils
import can.i.has.utils.Settable

import groovy.transform.Canonical

@Canonical
class DocumentSection implements Renderable, Settable<DocumentSection, Renderable> {
    SectionLevel level
    String title
    boolean ignored = false
    List<Renderable> content = []

    @Override
    String render() {
        (["\\$level${ignored?'*':''}{$title}"] + content.collect { it.render() }).join("\n")
    }

    @Override
    Set<String> getNeededPackages() {
        content.collect { it.neededPackages }.flatten()
    }

    @Override
    List<Class> getDelegateClasses() {
        [ContentCommands, StructureAPI, SectionAPI]
    }

    @Override
    void callback(Renderable result) {
        content.add(result)
    }
}
