package can.i.has.grex

import can.i.has.grex.compiler.GreXCompiler
import can.i.has.utils.DelegationUtils
import can.i.has.utils.builder.AttributeNodeBuilder


class GreX {
    final protected GreXCompiler compiler = new GreXCompiler()
    final protected AttributeNodeBuilder parser = new AttributeNodeBuilder()

    public def eval(Closure closure){
        //todo: wrap exceptions
        Node nodeTree = DelegationUtils.delegateTo(closure, parser)
//        Node nodeTree = this.@parser."$name"(*args)
        return compiler.compile(nodeTree)
    }
}
