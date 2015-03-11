package can.i.has.utils

import groovy.transform.Canonical


class IterUtils {
    static void zipEach(Iterable iterable1, Iterable iterable2, boolean fill=false, def fillWith=null, Closure closure){
        zipEach(iterable1.iterator(), iterable2.iterator(), fill, fillWith, closure)
    }

    static void zipEach(Iterator iterator1, Iterator iterator2, boolean fill=false, def fillWith=null, Closure closure){
        while (iterator1.hasNext() || iterator2.hasNext()){
            def x, y
            if (iterator1.hasNext())
                x = iterator1.next()
            else
                if (fill)
                    x = fillWith
                else
                    break
            if (iterator2.hasNext())
                y = iterator2.next()
            else
            if (fill)
                y = fillWith
            else
                break
            closure.call(x, y)
        }
    }

    static Map asMap(List<List> pairs){
        def out = [:]
        pairs.each {
            out[it[0]] = out[it[1]]
        }
        out
    }

//    static <K, V> Map<K, V> asMap(List<Pair<K, V>> pairs){
//        def out = [:]
//        pairs.each {
//            out[it.first] = out[it.second]
//        }
//    }

    static OrderedMap asOrderedMap(List<List> pairs){
        def out = [:]
        def order = []
        pairs.each {
            out[it[0]] = out[it[1]]
            order << it[0]
        }
        new OrderedMap(order, out)
    }

//    static <V> OrderedMap<V> asOrderedMap(List<Pair<String, V>> pairs){
//        def out = [:]
//        def order = []
//        pairs.each {
//            out[it.first] = out[it.second]
//            order << it.first
//        }
//        new OrderedMap(order, out)
//    }

    static <V> OrderedMap<V> asOrderedMap(List<String> order, Map<String, V> delegate){
        new OrderedMap<V>(order, delegate)
    }

//    @Canonical
    private static class CombinationsIterator<T> implements Iterator<OrderedMap<T>> {
        OrderedMap<List<T>> domains
        protected OrderedMap<T> currentValues = new OrderedMap<>(domains.@order, [:])
        protected OrderedMap<Iterator<T>> iterators = new OrderedMap<>(domains.@order, [:])
        protected boolean doesHaveNext = true

        CombinationsIterator(OrderedMap<List<T>> domains) {
            this.domains = domains
            domains.eachInOrder { String k, List<T> v ->
                iterators[k] = v.iterator()
                assert iterators[k].hasNext()
                currentValues[k] = iterators[k].next()
            }
        }


        @Override
        boolean hasNext() {
//            iterators.any { it.value.hasNext() }
            currentValues
        }

        @Override
        OrderedMap<T> next() {
            assert currentValues
            def out = new OrderedMap<T>(domains.@order, [:])
            currentValues.each { k, v ->
                out[k] = v
            }
            tick()
            return out
        }

        protected void inc(int i){
            if (iterators.listView()[i].hasNext())
                currentValues.listView()[i] = iterators.listView()[i].next()
            else {
                if (i>0) {
                    iterators.listView()[i] = domains.listView()[i].iterator()
                    currentValues.listView()[i] = iterators.listView()[i].next()
                    inc(i - 1)
                } else
                    currentValues = null
            }
        }

        protected void tick(){
            inc(domains.@order.size()-1)
        }

        @Override
        void remove() {
            throw new UnsupportedOperationException("Combination iterator does not support removing elements!")
        }
    }

    static <T> Iterator<OrderedMap<T>> combinationsIterator(OrderedMap<List<T>> domains){
        new CombinationsIterator<T>(domains)
    }

    static <T> Iterator<OrderedMap<T>> combinationsIterator(List<String> domainNames, Map<String, List<T>> domains){
        combinationsIterator(new OrderedMap<List<T>>(domainNames, domains))
    }
}
