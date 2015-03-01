package can.i.has.grex.latex.tables

interface ContentRenderer {
    String renderCell(def cellValue, int row, int col)
}
