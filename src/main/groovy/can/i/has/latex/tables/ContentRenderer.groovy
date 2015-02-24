package can.i.has.latex.tables

interface ContentRenderer {
    String renderCell(def cellValue, int row, int col)
}
