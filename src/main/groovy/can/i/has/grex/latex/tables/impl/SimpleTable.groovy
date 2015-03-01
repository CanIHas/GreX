package can.i.has.grex.latex.tables.impl

import can.i.has.grex.latex.tables.ContentRenderer
import can.i.has.grex.latex.tables.Sheet
import can.i.has.grex.latex.tables.SheetStyle

import groovy.transform.Canonical

@Canonical
class SimpleTable extends AbstractTable{
    Sheet sheet
    SheetStyle sheetStyle
    ContentRenderer contentRenderer
}
