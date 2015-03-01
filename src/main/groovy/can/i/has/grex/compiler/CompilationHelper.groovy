package can.i.has.grex.compiler

import can.i.has.grex.latex.model.Renderable
import can.i.has.grex.latex.model.contents.FileRenderable
import can.i.has.grex.latex.model.contents.StringRenderable
import can.i.has.grex.latex.model.structure.Command
import can.i.has.utils.MapArgsUtils


class CompilationHelper {
    static Command usePackage(Node node){
        def attrs = MapArgsUtils.leaveArgs(null, node.attributes(), "options", "package")
        List<String> options = attrs.options ?: []
        String val
        if (node.value()) {
            val = node.value()
//            assert val instanceof String
        } else {
            val = attrs.package
        }
        assert val //todo: exceptions
        new Command("usepackage", options, [new StringRenderable(val)])
    }

    static String escape(String latex){
        latex.replaceAll("[#\$%&\\^_{}~]\n", {
            switch (it) {
                case "\\": return "\\textbackslash{}"
                case "^": return "\\textasciicircum{}"
                case "~": return "\\textasciitilde{}"
                case "\n": return "\\newline"
                default: "\\$it"
            }
        })
    }

    static Renderable text(Node node){
        def attrs = MapArgsUtils.leaveArgs(null, node.attributes(), "text", "escape")
        boolean escapeArg = attrs.escape?: false
        String val
        if (node.value()) {
            val = node.value()
        } else {
            val = attrs.text
        }
        assert val //todo: exceptions
        if (escapeArg)
            val = escape(val)
        new StringRenderable(val)
    }

    static Renderable raw(Node node){
        def attrs = MapArgsUtils.leaveArgs(null, node.attributes(), "path", "escape", "neededPackages")
        boolean escape = attrs.escape?: false
        Collection<String> neededPackages = attrs.neededPackages ?: []
        def path
        if (node.value()) {
            path = node.value()
        } else {
            path = attrs.path
        }
        if (path instanceof String)
            path = [path]
        assert path instanceof List<String> //todo: generics :/ also, exceptions
        new FileRenderable(path, escape, neededPackages)
    }

}
