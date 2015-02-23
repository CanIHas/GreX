package can.i.has.utils

import java.lang.reflect.Method


trait Settable<T> {
    abstract List<Class> getDelegateClasses()
    abstract void callback(T result)

    //todo: try using use(CategoryClass) instead
    def methodMissing(String name, args) {
        Class clazz = delegateClasses.find{ it.methods.find { it.name == name } }
        Method method = clazz?.methods.find { it.name == name }
        if (clazz && method)
//            callback(method.invoke(null, args))
            callback(clazz."$name"(*args))
        else
            throw new FluentAPIException("Class ${this.class.simpleName} doesn't know how to react " +
                "to method $name with arguments $args")
    }

    def propertyMissing(String name, value) {
        methodMissing("set${name.capitalize()}", value)
    }
}