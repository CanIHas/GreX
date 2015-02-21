package can.i.has.tables.impl

import can.i.has.latex.model.Renderable
import can.i.has.tables.ContentRenderer


class ToStringRenderer implements ContentRenderer{
    @Override
    String renderCell(Object cellValue, int row, int col) {
        if (cellValue instanceof Renderable)
            return cellValue.render()
         cellValue==null ? "": "$cellValue"
    }

    static final ContentRenderer instance = new ToStringRenderer()
}
