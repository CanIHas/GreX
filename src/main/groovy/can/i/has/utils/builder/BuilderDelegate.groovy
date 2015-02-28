package can.i.has.utils.builder

import groovy.transform.Canonical

@Canonical
class BuilderDelegate {
    AttributeNodeBuilder realBuilder

    List toRemove = []
    List<List> toRemoveStack = []

    void setProperty(String name, Object val){
        realBuilder.currentNode.attributes()[name] = val
        toRemove.add(val)
    }

    public Object methodMissing(String methodName, Object args) {
        realBuilder.invokeMethod(methodName, args)
    }

    void beginClosure(Object node){
        toRemoveStack.push(toRemove)
        toRemove = []
    }

    void endClosure(Object node){
        if (node instanceof Node && node.value() instanceof List)
            ((List)node.value()).removeAll(toRemove)
        toRemove = toRemoveStack.pop()
    }
}
