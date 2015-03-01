package can.i.has.utils


class DelegationUtils {
    static <T> T delegateTo(Closure<T> closure, Object object){
        Closure<T> toCall = closure.clone()
        toCall.delegate = object
        toCall.resolveStrategy = Closure.DELEGATE_FIRST
        return toCall()
    }
}
