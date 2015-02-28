package can.i.has.utils.builder

/**
 * Silghtly modified NodeBuilder.
 * With this one you can assign nodes as attributes in closure.
 * Running this snippet:
 * <pre>
 * new NodePrinter().print(new NodeBuilder().foo(x:1){ y = bar() })
 * new NodePrinter().print(new AttributeNodeBuilder().foo(x:1){ y = bar() })
 * </pre>
 * gives following output:
 * <pre>
 * foo(x:1) {
 *   bar()
 * }
 * foo(x:1, y:bar[attributes={}; value=[]])
 * </pre>
 */
class AttributeNodeBuilder extends NodeBuilder {
    final protected BuilderDelegate delegate = new BuilderDelegate(this)

    protected Object createNode(Object name) {
        def out = super.createNode(name)
        delegate.beginClosure(out)
        out
    }

    protected Object createNode(Object name, Object value) {
        def out = super.createNode(name, value)
        delegate.beginClosure(out)
        out
    }

    protected Object createNode(Object name, Map attributes) {
        def out = super.createNode(name, attributes)
        delegate.beginClosure(out)
        out
    }

    protected Object createNode(Object name, Map attributes, Object value) {
        def out = super.createNode(name, attributes, value)
        delegate.beginClosure(out)
        out
    }

    protected void setClosureDelegate(Closure closure, Object node) {
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.setDelegate(delegate)
    }

    protected void nodeCompleted(Object parent, Object node) {
        delegate.endClosure(node)
    }
}
