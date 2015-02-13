package can.i.has.tables.trash

import can.i.has.tables.trash.CrossStyle
import can.i.has.tables.trash.LineStyle

/**
 * Imagine table:<br/>
 * <pre>
 * |---|---|-A-|---|
 * B . | . | . | . |
 * |---|-C-|---|---|
 * | . | . | . D . E
 * |-F-|---|---|---|
 * </pre>
 * <br/>
 * Dots stand for data in table. Signs | and --- draw borders between cells, letters as well.<br/>
 * columnSeparator(...) describe style of lines pictured with |. First two arguments define index of columns between
 *  which we need to specify separator, next one desribes number of row, and last one - total number of columns.<br/>
 *  rowSeparator(...) works in similiar fashion, but first two and last argument specify row, and middle one - column.<br/>
 *  If we need to describe first separator in row/column, first argument will be -1 and second 0. It works in the
 *  same way with last separator (first arg = number of columns/row - 1, second arg = number of columns/rows).<br/>
 * <br/>
 *  Following should be true:<br/>
 *  Style of line in A is returned with rowSeparator(-1, 0, 2, 2)<br/>
 *  Style of line in B is returned with columnSeparator(-1, 0, 0, 4)<br/>
 *  Style of line in C is returned with rowSeparator(0, 1, 1, 2)<br/>
 *  Style of line in D is returned with columnSeparator(2, 3, 1, 4)<br/>
 *  Style of line in E is returned with columnSeparator(3, 4, 1, 4)<br/>
 *  Style of line in F is returned with rowSeparator(1, 2, 0, 2)<br/>
 * <br/>
 *  Another component will take care of rendering this into LaTeX.<br/>
 */
interface OldSheetStyle {
    LineStyle leftSeparator(int row, int col, int width)
    LineStyle topSeparator(int row, int col, int height)
    CrossStyle upperLeftSeparator(int row, int col, int height, int width)
}
