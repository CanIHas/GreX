package can.i.has.tables.impl

import can.i.has.tables.Border

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@Singleton
@ToString
@EqualsAndHashCode
class BlankStyle implements DefaultStyleTrait{
    @Override
    Border getBorder(int row, int column, int height, int width) {
        Border.EMPTY
    }
}