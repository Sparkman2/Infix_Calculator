public class URQueue<E> {
    URLinkedList<E> list = new URLinkedList<>();

    public void enqueue(E e) {
        list.add(e);
    }

    public E peek() {
        if (this.list.head == null) {
            return null;
        } else {
            return this.list.peekFirst();
        }
    }

    public E dequeue() {
        if (this.list.head == null) return null;
        else return this.list.pollFirst();
    }

    public boolean isEmpty() {
        return (this.list.isEmpty());
    }

    public int getLength() {
        return this.list.size();
    }

    public Object[] toArray() {
        return this.list.toArray();
    }
}