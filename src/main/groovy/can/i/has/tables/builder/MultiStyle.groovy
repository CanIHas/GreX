package can.i.has.tables.builder

import can.i.has.tables.Border
import can.i.has.tables.Table
import can.i.has.tables.impl.DefaultStyleTrait

import groovy.transform.Canonical
import groovy.transform.Immutable

//@Immutable // fixme: figure out immutability and sheet copying
@Canonical
class MultiStyle implements DefaultStyleTrait{
    MultiTable multiTable

    @Override
    Border getBorder(int row, int column, int height, int width) {
        withDefault(row, column, height, width) {
            def pair = multiTable.tableStack.resolve(column, row)
            if (pair==null)
                return defaultBorder
            def coord = pair.first.relative(column, row)
            def table = pair.second
            table.sheetStyle.getBorder(coord.second, coord.first, table.sheet.rows, table.sheet.columns)
        }
    }


    @Override
    public String toString() {
        return "MultiStyle{" +
            "multiTable=" + multiTable +
            '}';
    }
}
