package can.i.has.latex.tables.impl

import can.i.has.latex.tables.ContentRenderer
import can.i.has.latex.tables.Sheet
import can.i.has.latex.tables.SheetStyle

import groovy.transform.Canonical

@Canonical
class SimpleTable extends AbstractTable{
    Sheet sheet
    SheetStyle sheetStyle
    ContentRenderer contentRenderer
}
