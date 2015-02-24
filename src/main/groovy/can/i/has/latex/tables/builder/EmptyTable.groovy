package can.i.has.latex.tables.builder

import can.i.has.latex.tables.ContentRenderer
import can.i.has.latex.tables.Sheet
import can.i.has.latex.tables.SheetStyle
import can.i.has.latex.tables.impl.AbstractTable
import can.i.has.latex.tables.impl.BlankStyle
import can.i.has.latex.tables.impl.SimpleSheet
import can.i.has.latex.tables.impl.ToStringRenderer

import groovy.transform.Canonical

@Singleton
@Canonical
class EmptyTable<T> extends AbstractTable<T> {
    final Sheet sheet = new SimpleSheet(1, 1, null)
    final SheetStyle sheetStyle = BlankStyle.instance
    final ContentRenderer contentRenderer = ToStringRenderer.instance
}
