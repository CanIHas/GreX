package can.i.has.tables.impl

import can.i.has.tables.ContentRenderer
import can.i.has.tables.Sheet
import can.i.has.tables.SheetStyle

import groovy.transform.Canonical

@Canonical
class SimpleTable extends AbstractTable{
    Sheet sheet
    SheetStyle sheetStyle
    ContentRenderer contentRenderer
}
