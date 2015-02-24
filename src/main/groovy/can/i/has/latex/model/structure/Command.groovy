package can.i.has.latex.model.structure

import can.i.has.latex.model.Renderable

import groovy.transform.Canonical

@Canonical
class Command implements Renderable{

    String name
    List<String> options
    List<Renderable> args
    Set<String> neededPackages = [].toSet()

    @Override
    String render() {
        def out = "\\" << name
        if (options)
            out << "[" << options.join(",") << "]"
        if (args)
            out << args.collect { "{${it.render()}}" }.join("")
        out.toString()
    }

}
