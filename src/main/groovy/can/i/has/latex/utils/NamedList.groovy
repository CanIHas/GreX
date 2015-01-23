package can.i.has.latex.utils

import groovy.transform.Canonical

@Canonical
class NamedList<T>{
    List<String> names
    @Delegate List<T> delegate

    T get(String name){
        int idx = names.indexOf(name)
        if (idx<0)
            throw new IndexOutOfBoundsException("Unknown name: $name; known names: $names")
        delegate.get(idx)
    }

    T set(String name, T element){
        int idx = names.indexOf(name)
        if (idx<0)
            throw new IndexOutOfBoundsException("Unknown name: $name; known names: $names")
        delegate.set(idx, element)
    }
}
