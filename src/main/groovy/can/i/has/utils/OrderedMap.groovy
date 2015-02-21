package can.i.has.utils

import groovy.transform.Canonical

@Canonical
class OrderedMap<T> implements Map<String, T>, Serializable{
    List<String> order
    @Delegate Map<String, T> delegate

    boolean containsKey(Integer i) {
        return i<this.@order.size()
    }

    T get(Integer i) {
        return this.@delegate.get(this.@order.get(i))
    }

    T put(Integer i, T value) {
        return this.@delegate.put(this.@order.get(i), value)
    }

    T remove(Integer i) {
        return this.@delegate.remove(this.@order.remove(i))
    }

    @Override
    void putAll(Map<? extends String, ? extends T> m) { // first generic parameter may be int or string
        m.each { i, v ->
            this.put(i, v)
        }
    }

    Set<Integer> indicesSet() {
        return [0..this.@order.size()-1]
    }

    void trim(){
        def toDelete = this.@delegate.keySet()
        toDelete.removeAll(this.@order)
        toDelete.each {
            this.@delegate.remove(it)
        }
    }

    OrderedMap<T> eachInOrder(Closure closure){
        order.each {
            closure.call(it, this.@delegate.get(it))
        }
    }

    OrderedMap<T> eachInOrderWithIndex(Closure closure){
        order.eachWithIndex { String entry, int i ->
            closure.call(i, entry, this.@delegate.get(entry))
        }
    }

    OrderedMap<T> each(Closure closure){
        this.@delegate.each closure
    }

    List valuesList(){
        this.@order.collect { this.@delegate.get(it) }
    }
}
