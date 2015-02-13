package can.i.has.tables

import groovy.transform.Canonical

@Canonical
class SimpleTable extends AbstractTable{

    Sheet sheet
    SheetStyle sheetStyle
    ContentRenderer contentRenderer

}
