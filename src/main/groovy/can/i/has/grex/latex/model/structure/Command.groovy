package can.i.has.grex.latex.model.structure

import can.i.has.grex.Workspace
import can.i.has.grex.latex.model.Renderable

import groovy.transform.Canonical

@Canonical
class Command implements Renderable{

    String name
    List<String> options
    List<Renderable> args
    Set<String> neededPackages = [].toSet()

    @Override
    String render(Workspace workspace) {
        def out = "\\" << name
        if (options)
            out << "[" << options.join(",") << "]"
        if (args)
            out << args.collect { "{${it.render(workspace)}}" }.join("")
        out.toString()
    }

}
