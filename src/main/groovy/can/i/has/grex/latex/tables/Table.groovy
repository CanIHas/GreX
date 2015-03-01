package can.i.has.grex.latex.tables

import can.i.has.grex.latex.model.Renderable

interface Table<T> extends Renderable{
    Sheet<T> getSheet()// throws UnsupportedOperationException
    SheetStyle getSheetStyle()
    ContentRenderer getContentRenderer()
}