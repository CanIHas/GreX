package can.i.has.grex.latex.model

import can.i.has.grex.Workspace


interface Renderable {
    String render(Workspace workspace)
    Set<String> getNeededPackages()
}