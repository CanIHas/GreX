package can.i.has.tables

import can.i.has.latex.model.Renderable

interface Table<T> extends Renderable{
    Sheet<T> getSheet()// throws UnsupportedOperationException
    SheetStyle getSheetStyle()
    ContentRenderer getContentRenderer()
}