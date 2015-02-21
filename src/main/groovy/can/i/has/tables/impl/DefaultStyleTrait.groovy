package can.i.has.tables.impl

import can.i.has.tables.Border
import can.i.has.tables.SheetStyle

trait DefaultStyleTrait implements SheetStyle{
    Border getDefaultBorder(){
        Border.EMPTY
    }

    Border withDefault(int row, int column, int height, int width, Closure<Border> closure){
        if (row<0 || column<0 || row>=height || column>=width)
            return defaultBorder
        def out = closure.call()
        return out
    }
}
