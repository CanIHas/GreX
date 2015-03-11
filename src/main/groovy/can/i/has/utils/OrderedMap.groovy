package can.i.has.utils

import groovy.transform.Canonical

@Canonical
class OrderedMap<T> implements Map<String, T>, Serializable{
    List<String> order
    @Delegate Map<String, T> delegate

    List<T> listView(){
        new AbstractList<T>(){

            @Override
            T get(int i) {
                OrderedMap.this.@delegate.get(OrderedMap.this.@order[i])
            }

            @Override
            public T set(int index, T element) {
                OrderedMap.this.@delegate.put(OrderedMap.this.@order[index], element)
            }

            @Override
            public T remove(int index) {
                OrderedMap.this.@delegate.remove(OrderedMap.this.@order[index])
            }

            @Override
            int size() {
                OrderedMap.this.@order.size()
            }
        }
    }

    boolean containsKey(Integer i) {
        return i<this.@order.size()
    }

    @Override
    void putAll(Map<? extends String, ? extends T> m) { // first generic parameter may be int or string
        m.each { i, v ->
            if (i instanceof Integer)
                this.listView().set(i, v)
            else
                this.@delegate.put(i, v)
        }
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
        this.@delegate.each(closure)
        this
    }

    List valuesList(){
        this.@order.collect { this.@delegate.get(it) }
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        OrderedMap that = (OrderedMap) o

        if (listView() != that.listView()) return false

        return true
    }

    int hashCode() {
        int result
        result = 7 + 11*listView().hashCode()
        return result
    }
}
