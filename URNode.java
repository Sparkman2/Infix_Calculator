class URNode<E> {

    private E e;
    private URNode<E> n;
    private URNode<E> p;

    URNode(E it, URNode<E> inp, URNode<E> inn) { e = it; p = inp; n = inn; }
    URNode(URNode<E> inp, URNode<E> inn) { p = inp; n = inn; }

    public E element() { return e; }
    public E setElement(E it) { return e = it; }
    public URNode<E> next() { return n; }
    public URNode<E> setNext(URNode<E> nextval) { return n = nextval; }
    public URNode<E> prev() { return p; }
    public URNode<E> setPrev(URNode<E> prevval) { return p = prevval; }

}