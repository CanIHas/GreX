package can.i.has.latex.model


interface Renderable {
    String render()
    Set<String> getNeededPackages()
}