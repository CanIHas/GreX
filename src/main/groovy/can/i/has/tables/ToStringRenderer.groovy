package can.i.has.tables

import can.i.has.latex.model.Renderable


class ToStringRenderer implements ContentRenderer{
    @Override
    String renderCell(Object cellValue, int row, int col) {
        if (cellValue instanceof Renderable)
            return cellValue.render()
        "$cellValue"
    }
}
