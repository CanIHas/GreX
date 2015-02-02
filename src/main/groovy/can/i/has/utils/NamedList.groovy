package can.i.has.utils

import groovy.transform.Canonical

@Canonical
class NamedList<T> implements Serializable{
    List<String> names
    @Delegate List<T> delegate

    static final long serialVersionUID = 42L;

    T getAt(String name){
        int idx = names.indexOf(name)
        if (idx<0)
            throw new IndexOutOfBoundsException("Unknown name: $name; known names: $names")
        delegate.get(idx)
    }

    T putAt(String name, T element){
        int idx = names.indexOf(name)
        if (idx<0)
            throw new IndexOutOfBoundsException("Unknown name: $name; known names: $names")
        delegate.set(idx, element)
    }
}
