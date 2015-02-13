package can.i.has.tables.trash.old_tables

import can.i.has.latex.model.Renderable
import can.i.has.tables.trash.old_tables.impl.RenderableBuilderContext

import groovy.transform.Canonical

@Canonical
class ColumnTable{

    List<String> header
    List<List> data

    /**
     * Return configured Renderable table
     * @param configure closure with no args, delegating to RenderableBuilderContext
     * @return ColumnTableRenderable
     */
    Renderable toLaTeX(Closure<Void> configure = {}){
        new RenderableBuilderContext(this).withDefaultsInitialized().configure(configure).build()
    }
}
