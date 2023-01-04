public class URStack<E> {
    URLinkedList<E> list = new URLinkedList<>();

    public void push(E e) {
        if (this.list.size() == 0) this.list.add(e);
        else this.list.add(0,e);
    }

    public E pop() {
        return this.list.remove(0);
    }

    public E peek() {
        if (this.list.head == null) {
            return null;
        }
        else {
            return this.list.peekFirst();
        }
    }

    public boolean isEmpty() {
        return (this.list.isEmpty());
    }
}