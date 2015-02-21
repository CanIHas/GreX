package can.i.has.tables.builder

import can.i.has.tables.ContentRenderer

import groovy.transform.Canonical
import groovy.transform.Immutable

//@Immutable // fixme: figure out immutability and sheet copying
@Canonical
class MultiRenderer implements ContentRenderer{
    MultiTable multiTable

    @Override
    String renderCell(def cellValue, int row, int col) {
        def pair = multiTable.tableStack.resolve(col, row)
        if (pair == null)
            return ""
        def coord = pair.first.relative(col, row)
        def table = pair.second
        return table.contentRenderer.renderCell(cellValue, coord.second, coord.first)
    }


    @Override
    public String toString() {
        return "MultiRenderer{" +
            "multiTable=" + multiTable +
            '}';
    }
}
