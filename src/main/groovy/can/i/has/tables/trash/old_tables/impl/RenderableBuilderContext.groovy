package can.i.has.tables.trash.old_tables.impl

import can.i.has.latex.model.Renderable
import can.i.has.latex.model.StringRenderable
import can.i.has.tables.trash.old_tables.Border
import can.i.has.tables.trash.old_tables.ColumnTable

import groovy.transform.Canonical

@Canonical
class RenderableBuilderContext {
    ColumnTable table

    Closure<Renderable> defaultHeaderRenderer
    Map<String, Closure<Renderable>> headerRenderers
    Closure<Renderable> defaultContentRenderer
    Map<String, Closure<Renderable>> contentRenderers
    String defaultColumnPositioner
    Map<String, String> columnPositioners
    Map<Border.Part, Border.Style> borderFormat

    RenderableBuilderContext withDefaultsInitialized(){
        this.@defaultHeaderRenderer = { String colHeader ->
            new StringRenderable(colHeader)
        }
        this.@headerRenderers = [:].withDefault {
            return this.defaultHeaderRenderer
        }

        this.@defaultContentRenderer = { def colValue ->
            new StringRenderable("$colValue")
        }
        this.@contentRenderers = [:].withDefault {
            return this.defaultContentRenderer
        }

        this.@defaultColumnPositioner = "c"
        this.@columnPositioners = [:].withDefault {
            return this.defaultColumnPositioner
        }

        this.@borderFormat = [:]
        Border.injectDefaults(this.@borderFormat)

        return this
    }

    RenderableBuilderContext configure(Closure closure){
        this.with(closure)
        return this
    }

    ColumnTableRenderable build(){
        def columnSpec = borderFormat[Border.Part.LEFT].vertical +
            table.header.collect { String colHeader ->
                columnPositioners[colHeader]
            }.join(borderFormat[Border.Part.COLUMNS].vertical) +
            borderFormat[Border.Part.RIGHT].vertical
        def template = (["%s"]*table.header.size()).join(" & ")+" \\\\"
        def renderedHeader = table.header.collect {
            headerRenderers[it].call(it).render()
        }
        def headerTxt = String.format(template, renderedHeader.toArray())
        def content = [
            new StringRenderable(borderFormat[Border.Part.TOP].horizontal),
            new StringRenderable(headerTxt),
            new StringRenderable(borderFormat[Border.Part.HEADER_ROW].horizontal)
        ]
        table.data.eachWithIndex { List row, int i ->
            def rendered = []
            row.eachWithIndex { def cell, int j ->
                rendered << contentRenderers[table.header[j]].call(cell).render()
            }
            content << new StringRenderable(String.format(template, rendered.toArray()))
            def borderPart = (i==table.data.size()-1) ? Border.Part.ROWS : Border.Part.BOTTOM
            content << new StringRenderable(borderFormat[borderPart].horizontal)
        }
        new ColumnTableRenderable(columnSpec, content)
    }
}
