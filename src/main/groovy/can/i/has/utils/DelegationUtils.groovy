package can.i.has.utils


class DelegationUtils {
    static void delegateTo(Closure closure, Object object){
        Closure toCall = closure.clone()
        toCall.delegate = object
        toCall.resolveStrategy = Closure.DELEGATE_FIRST
        toCall()
    }
}
