package can.i.has.utils


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
}
