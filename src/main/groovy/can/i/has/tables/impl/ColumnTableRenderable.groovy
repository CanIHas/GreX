package can.i.has.tables.impl

import can.i.has.latex.model.Environment
import can.i.has.latex.model.Renderable
import can.i.has.latex.model.StringRenderable

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString


@ToString
@EqualsAndHashCode
class ColumnTableRenderable extends Environment {
    ColumnTableRenderable(String columnSpec, List<Renderable> content) {
        super("tabular", [new StringRenderable(columnSpec)], [], content)
    }
}
