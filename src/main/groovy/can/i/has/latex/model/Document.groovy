package can.i.has.latex.model

import can.i.has.latex.model.structure.Command
import can.i.has.latex.model.structure.Environment
import can.i.has.latex.model.structure.Preamble
import can.i.has.utils.DelegationUtils

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static can.i.has.latex.model.fluent.StructureAPI.documentEnv
import static can.i.has.latex.model.fluent.PreambleCommands.documentClass as DC
import static can.i.has.latex.model.fluent.PreambleCommands.usePackage

@EqualsAndHashCode
@ToString
class Document implements Renderable{
    final Command documentClass
    final Preamble preamble = new Preamble()
    final Environment content = documentEnv()

    Document(Command documentClass=DC(), List<Command> preamble=[], List<Renderable> content=[]) {
        this.documentClass = documentClass
        this.preamble.commands += preamble
        this.content.content += content
    }

    @Override
    String render() {
            (
                [ documentClass ] +
                neededPackages.collect {
                    usePackage(it)
                } +
                preamble
            ).collect {
                it.render()
            }.join("\n")+
            "\n"+
            content.render()
    }

    @Override
    Set<String> getNeededPackages() {
        content.neededPackages
    }

    Document withPreamble(Closure c){
        DelegationUtils.delegateTo(c, preamble)
        this
    }

    Document withContent(Closure c){
        DelegationUtils.delegateTo(c, content)
        this
    }
}
