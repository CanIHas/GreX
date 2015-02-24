package can.i.has.latex.model.structure

import can.i.has.latex.model.fluent.StructureAPI
import can.i.has.latex.model.fluent.PreambleCommands
import can.i.has.utils.Settable


class Preamble implements Settable<Preamble, Command>{
    List<Command> commands = []

    String render(){
        commands.collect { it.render() }.join("\n")
    }

    @Override
    List<Class> getDelegateClasses() {
        [PreambleCommands, StructureAPI]
    }

    @Override
    void callback(Command result) {
        commands.add(result)
    }
}
