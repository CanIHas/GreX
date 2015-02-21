package can.i.has.tables

interface ContentRenderer {
    String renderCell(def cellValue, int row, int col)
}
