package can.i.has.tables

import can.i.has.latex.model.Renderable


interface ContentRenderer {
    String renderCell(def cellValue, int row, int col)
}
