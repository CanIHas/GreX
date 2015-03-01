package can.i.has.grex.latex.tables.impl

import can.i.has.grex.latex.model.Renderable
import can.i.has.grex.latex.tables.ContentRenderer


class ToStringRenderer implements ContentRenderer{
    @Override
    String renderCell(Object cellValue, int row, int col) {
        if (cellValue instanceof Renderable)
            return cellValue.render()
         cellValue==null ? "": "$cellValue"
    }

    static final ContentRenderer instance = new ToStringRenderer()
}
