package can.i.has.utils

import org.slf4j.Logger

import groovy.util.logging.Slf4j

@Slf4j
class MapArgsUtils {
    static <V> Map<String, V> leaveArgs(Logger logger=null, Map<String, V> original, String... needed){
        Map<String, V> out = [:]
        original.each { String k, V v ->
            if (needed.contains(k))
                out[k] = v
            else
                log?.warn("This method doesn't take named argument $k, yet it is provided with value $v!")
        }
        out
    }
}
