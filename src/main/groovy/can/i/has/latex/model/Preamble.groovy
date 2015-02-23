package can.i.has.latex.model

import can.i.has.latex.CommonAPI
import can.i.has.latex.PreambleCommands
import can.i.has.utils.Settable


class Preamble implements Settable<Command>{
    List<Command> commands = []

    String render(){
        commands.collect { it.render() }.join("\n")
    }

    @Override
    List<Class> getDelegateClasses() {
        [PreambleCommands, CommonAPI]
    }

    @Override
    void callback(Command result) {
        commands.add(result)
    }
}
