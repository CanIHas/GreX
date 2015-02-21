package can.i.has.tables.builder

import can.i.has.tables.ContentRenderer
import can.i.has.tables.Sheet
import can.i.has.tables.SheetStyle
import can.i.has.tables.impl.AbstractTable

import groovy.transform.Canonical

//import groovy.transform.Immutable
//
//@Immutable // fixme: figure out immutability and sheet copying
@Canonical
class MultiTable<T> extends AbstractTable<T>{

    MultiTable(TableStack<T> tableStack) {
        this.tableStack = tableStack
    }
    TableStack<T> tableStack
    T defaultValue = null

    Sheet<T> sheet = new MultiSheet<T>(this)
    SheetStyle sheetStyle = new MultiStyle(this)
    ContentRenderer contentRenderer = new MultiRenderer(this)


    @Override
    public String toString() {
        return "MultiTable{" +
            "tableStack=" + tableStack +
            ", defaultValue=" + defaultValue +
            '}';
    }
}
