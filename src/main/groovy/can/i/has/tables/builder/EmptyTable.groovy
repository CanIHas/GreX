package can.i.has.tables.builder

import can.i.has.tables.ContentRenderer
import can.i.has.tables.Sheet
import can.i.has.tables.SheetStyle
import can.i.has.tables.impl.AbstractTable
import can.i.has.tables.impl.BlankStyle
import can.i.has.tables.impl.SimpleSheet
import can.i.has.tables.impl.ToStringRenderer

import groovy.transform.Canonical

@Singleton
@Canonical
class EmptyTable<T> extends AbstractTable<T> {
    final Sheet sheet = new SimpleSheet(1, 1, null)
    final SheetStyle sheetStyle = BlankStyle.instance
    final ContentRenderer contentRenderer = ToStringRenderer.instance
}
