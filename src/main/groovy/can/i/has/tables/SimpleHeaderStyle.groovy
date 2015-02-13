package can.i.has.tables

import groovy.transform.Canonical

@Canonical
class SimpleHeaderStyle implements DefaultStyleTrait{
    int border = 1
    int headerToContent = 2
    int headerColumns = 0
    int rows = 0
    int columns = 1

    @Override
    Border getBorder(int row, int column, int height, int width) {
        withDefault(row, column, height, width) {
            Border out = new Border(rows, rows, columns, columns)
            if (row == 0) {
                out.top = border
                out.left = headerColumns
                out.right = headerColumns
                out.bottom = headerToContent
            }
            if (row == height-1) {
                out.bottom = border
            }
            if (column == 0)
                out.left = border
            if (column == width - 1)
                out.right = border
            return out
        }
    }
}
