package can.i.has.latex.model.builder

abstract class AbstractTypeBuilder<T> {
    T build(Map props, Closure closure){
        T out = factory()
        props.each { k, v ->
            out."$k" = v
        }
        Helper<T> helper = getHelper(out)
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.delegate = helper
        closure()
        out
    }

    abstract T factory()
    abstract Helper<T> getHelper(T out)

    static interface Helper<T2> {
        void setObject(T2 object)
        T2 getObject()
    }

    static abstract class AbstractHelper<T2> implements Helper<T2>{
        T2 object
    }
}
