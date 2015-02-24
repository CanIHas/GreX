package can.i.has.latex.model.structure

import can.i.has.latex.model.Renderable
import can.i.has.latex.model.fluent.StructureAPI
import can.i.has.latex.model.fluent.ContentCommands
import can.i.has.latex.model.sections.SectionAPI
import can.i.has.utils.Settable

import groovy.transform.Canonical

@Canonical
class Group implements Renderable, Settable<Group, Renderable> {
    List<Renderable> content


    @Override
    String render() {
        "{"+content.collect {it.render()}.join("""
""")+"}"
    }

    @Override
    Set<String> getNeededPackages() {
        content.collect { it.neededPackages }.flatten().toSet()
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
