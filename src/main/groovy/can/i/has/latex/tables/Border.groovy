package can.i.has.latex.tables

import groovy.transform.Canonical

import static java.lang.Math.max

@Canonical
class Border {
    //todo: ensure domains (0, 1, 2)
    int top = 1
    int bottom = 1
    int left = 1
    int right = 1

    String getLeftSeparator(){
        "|"*left
    }
    String getRightSeparator(){
        "|"*right
    }

    String getTopRecomendedSeparator(){
        getLineOfWidth(top)
    }

    String getBottomRecomendedSeparator(){
        getLineOfWidth(bottom)
    }

    static final protected List<String> lines = ["~", "-", "="].asImmutable()

    static String getLineOfWidth(int width){
        lines[width]
//        switch (width) { // how the hell would I think of this? oO
//            case 0: return "~"
//            case 1: return "-"
//            case 2: return "="
//        }
    }

    /**
     * "above" row
     * "this" row
     * @param above
     * @return
     */
    String getBordeWithCellAbove(Border above){
        getLineOfWidth(max(above.bottom, top))
    }

    /**
     * "this"
     * "below" row
     * @param below
     * @return
     */
    String getBorderWithCellBelow(Border below){
        getLineOfWidth(max(below.top, bottom))
    }

    Border copy(){
        new Border(top, bottom, left, right)
    }

    static final Border EMPTY = getEmpty()

    static Border getEmpty(){
        new Border(0, 0, 0, 0)
    }
}
