package can.i.has.latex.model

import can.i.has.latex.experiments.Workspace

import groovy.transform.Canonical

@Canonical
class RawLaTeXFile implements DocumentElement{
    File file

    static forPath(String... path){
        new RawLaTeXFile(Workspace.activeWorkspace.rawFile(path))
    }

    @Override
    String render() {
        file.text
    }
}
