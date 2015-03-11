package can.i.has.grex.latex.model

import can.i.has.grex.Workspace
import can.i.has.grex.compiler.CompilationHelper
import can.i.has.grex.latex.model.contents.StringRenderable
import can.i.has.grex.latex.model.structure.Command
import can.i.has.grex.latex.model.structure.Environment
import can.i.has.grex.latex.model.structure.Preamble

import groovy.transform.Canonical
import groovy.util.logging.Slf4j

import static can.i.has.utils.MapArgsUtils.leaveArgs

@Canonical
@Slf4j
class Document implements Renderable {
    String name
    //ugly name, but for the sake of latex consistency we'll keep it
    private Command documentClass = new Command("documentclass", [], [new StringRenderable("article")])
    Preamble preamble
    Environment body

    void setDocumentBody(Environment body){
        this.body = body
    }

    Command getDocumentclass() {
        return documentClass
    }

    void setDocumentclass(String style) {
        documentClass = new Command("documentclass", [], [new StringRenderable(style)])
    }

    void setDocumentclass(Command documentClass) {
        this.documentClass = documentClass
    }

    void setDocumentclass(Map args) {
        args = leaveArgs(log, args, "style", "options")
        if (!args.style instanceof Renderable)
            args.style = new StringRenderable("${args.style}")
        documentclass = new Command("documentclass", args.options, [args.style])
    }

    @Override
    String render(Workspace workspace) {
        def tempBuilder = new NodeBuilder()
        ( [
            documentclass.render(workspace),
        ] + neededPackages.collect {
            CompilationHelper.usePackage(tempBuilder.usePackage(it)).render(workspace)
        } + preamble.render(workspace) +
            body.render(workspace) ).join("\n")
    }

    @Override
    Set<String> getNeededPackages() {
        body?.neededPackages ?: [].toSet()
    }
}
