package can.i.has.utils

trait Settable<SELF, T> {
    abstract List<Class> getDelegateClasses()
    abstract void callback(T result)

    //todo: try using use(CategoryClass) instead
    def methodMissing(String name, args) {
        Class clazz = delegateClasses.find{ it.methods.find { it.name == name } }
//        Method method = clazz?.methods.find { it.name == name }
//        if (clazz && method)
            callback(clazz."$name"(*args))
//        else
//            throw new FluentAPIException("Class ${this.class.simpleName} doesn't know how to react " +
//                "to method $name with arguments $args")
    }

    def propertyMissing(String name, value) {
        methodMissing("set${name.capitalize()}", value)
    }

    SELF with(Closure closure){
        DelegationUtils.delegateTo(closure, this)
        this
    }
}