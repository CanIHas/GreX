package can.i.has.utils


interface WithContent<T> {
    T getContent()
    void setContent(T content)
}