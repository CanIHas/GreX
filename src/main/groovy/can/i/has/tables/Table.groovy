package can.i.has.tables

import can.i.has.latex.model.Renderable

/**
 * TODO: THIS IS IT http://tex.stackexchange.com/questions/10121/how-to-make-double-cline-in-tables
 */
interface Table extends Renderable{
    Sheet getSheet()
    SheetStyle getSheetStyle()
    ContentRenderer getContentRenderer()
}