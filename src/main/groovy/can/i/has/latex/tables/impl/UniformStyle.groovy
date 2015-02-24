package can.i.has.latex.tables.impl

import can.i.has.latex.tables.Border

import groovy.transform.Canonical

@Canonical
class UniformStyle implements DefaultStyleTrait{
    int borders = 1
    int midColumn = 1
    int midRow = 1

    @Override
    Border getBorder(int row, int column, int height, int width) {
        withDefault(row, column, height, width) {
            Border out = new Border(0, midRow, 0, midColumn)
            if (row == 0) {
                out.top = borders
            }
            if (row == height-1) {
                out.bottom = borders
            }
            if (column == 0)
                out.left = borders
            if (column == width - 1)
                out.right = borders
            return out
        }
    }
}
