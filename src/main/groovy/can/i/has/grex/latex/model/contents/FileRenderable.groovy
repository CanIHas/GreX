package can.i.has.grex.latex.model.contents

import can.i.has.grex.Workspace
import can.i.has.grex.compiler.CompilationHelper
import can.i.has.grex.latex.model.Renderable

import groovy.transform.Canonical

@Canonical
class FileRenderable implements Renderable{
    List<String> path
    boolean escape = false
    List<String> neededPackages = []

    @Override
    String render(Workspace workspace) {
        def text = workspace.rawFile(path.toArray() as String[]).text.trim()
        escape ? CompilationHelper.escape(text) : text
    }

    @Override
    Set<String> getNeededPackages() {
        return neededPackages.toSet()
    }
}
