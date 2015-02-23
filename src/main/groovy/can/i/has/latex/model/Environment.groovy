package can.i.has.latex.model

import can.i.has.latex.CommonAPI
import can.i.has.latex.ContentCommands
import can.i.has.utils.Settable

import groovy.transform.Canonical

import static can.i.has.latex.ContentCommands.*

@Canonical
class Environment extends Group implements Settable<Renderable>{
    String name
    List<Renderable> beginArgs
    List<Renderable> endArgs

    Environment(String name, List<Renderable> beginArgs = [], List<Renderable> endArgs = [], List<Renderable> content) {
        super(content)
        this.name = name
        this.beginArgs = beginArgs
        this.endArgs = endArgs
    }

    Environment(HashMap args) {
        super(args)
        this.name = args?.name
    }

    @Override
    String render() {
        ([
            begin(name, beginArgs.toArray() as Renderable[])
        ] + content + [
            end(name, endArgs.toArray() as Renderable[])
        ]).collect {it.render()}.join("""
""")
    }

    @Override
    List<Class> getDelegateClasses() {
        [ContentCommands, CommonAPI]
    }

    @Override
    void callback(Renderable result) {
        content.add(result)
    }

    Environment with(Closure closure){
        def toCall = closure.clone()
        toCall.delegate = this
        toCall.resolveStrategy = Closure.DELEGATE_FIRST
        toCall()
        this
    }
}
