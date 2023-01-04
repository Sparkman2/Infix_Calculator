import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class URLinkedList<E> implements URList<E>, Iterable<E> {
    URNode<E> head = null;
    URNode<E> tail = null;
    int size = 0;

    public boolean add(E e) {
        URNode<E> newNode = new URNode<>(e, null, null);
        try {
            if (this.head == null) this.head = newNode;
            else {
                newNode.setPrev(this.tail);
                this.tail.setNext(newNode);
            }
            this.tail = newNode;
            this.size++;
            return true;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public void add(int index, E element) {
        try {
            URNode<E> newNode = new URNode<>(element, null, null);
            URNode<E> h = this.head;
            for (int i=0;i<index-1;i++) {
                if (h==null) {
                    throw new Exception("cannot add at a null index");
                }
                h = h.next();
            }
            if (h.prev() == null) {
                this.head = newNode;
            }
            else {
                newNode.setPrev(h.prev());
                h.prev().setNext(newNode);
            }
            if (h == null) {
                this.tail = newNode;
            }
            else {
                newNode.setNext(h);
                h.setPrev(newNode);
            }
            this.size++;
        }
        catch (Exception err) {
            System.err.println(err);
        }
    }

    public boolean addAll(Collection c) {
        try {
            Iterator<E> it = (Iterator<E>) c.iterator();
            if (!it.hasNext()) return true;
            URNode<E> newNode = new URNode<>(it.next(),null,null);
            if (this.head == null) this.head = newNode;
            else {
                this.tail.setNext(newNode);
                newNode.setPrev(this.tail);
            }
            while (it.hasNext()) {
                E e = it.next();
                URNode<E> nextNode = new URNode<>(e, null, null);
                newNode.setNext(nextNode);
                nextNode.setPrev(newNode);
                newNode = nextNode;
            }
            this.tail = newNode;
            this.size += c.size();
            return true;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public boolean addAll(int index, Collection c) {
        try {
            Iterator<E> it = (Iterator<E>) c.iterator();
            if (!it.hasNext()) {
                return true;
            }
            E e = it.next();
            URNode<E> newNode = new URNode<>(e, null, null);
            URNode<E> node = this.head;
            for (int i=0; i<index; i++) {
                if (node == null) {
                    throw new Exception("This index is null");
                }
                node = node.next();
            }
            if (node.prev() == null) {
                this.head = newNode;
            }
            else {
                node.prev().setNext(newNode);
                newNode.setPrev(node.prev());
            }
            while (it.hasNext()) {
                URNode<E> newNode2 = new URNode<>(e, null, null);
                newNode.setNext(newNode2);
                newNode2.setPrev(newNode);
                newNode = newNode2;
            }
            // add the index node
            if (node == null) {
                this.tail = newNode;
            }
            else {
                newNode.setNext(node);
                node.setPrev(newNode);
            }
            this.size += c.size();
            return true;
        }
        catch (Exception err) {
            System.err.println(err);
            return false;
        }
    }

    public void clear() {
        try {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public boolean contains(Object o) {
        try {
            URNode<E> oNode = (URNode<E>) o;
            URNode<E> node = this.head;
            while (node != null) {
                if (node.element()==oNode.element()) return true;
                node = node.next();
            }
            return false;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public boolean containsAll(Collection<?> c) {
        try {
            for (Iterator<E> it = (Iterator<E>) c.iterator(); it.hasNext();) {
                E e = it.next();
                // If some nodes don't work, it does not contain "all"
                if (!this.contains(e)) {
                    return false;
                }
            }
            return true;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public boolean equals(Object o) {
        //Cast to LinkedList
        URLinkedList<E> oList = (URLinkedList<E>) o;
        URLinkedList<E> copy = new URLinkedList<>();
        copy.addAll(Arrays.asList((E[]) this.toArray()));
        try {
            //Try to remove nodes from oList, iterating through copy
            URNode<E> node = copy.head;
            while (node!=null) {
                if(!oList.remove(node.element())) {
                    return false;
                }
                node = node.next();
            }
            if (oList.size()>0) return false;
            return true;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public E get(int index) {
        try {
            URNode<E> node = this.head;
            for (int i=0;i<index;i++) {
                if (node == null) throw new Exception("There is no element at index " + index);
                node = node.next();
            }
            if (node == null) throw new Exception("There is no element at index " + index);
            return node.element();
        }
        catch (Exception err) {
            System.err.println(err);
            return null;
        }
    }

    public int indexOf(Object o) {
        URNode<E> oNode = (URNode<E>) o;
        URNode<E> node = this.head;
        int index = 0;
        while (node!=null) {
            if (node.element() == oNode.element()) return index;
            node = node.next();
            index++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return (this.head == null && this.tail == null);
    }

    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < size() && get(current) != null;
            }

            @Override
            public E next() {
                return get(current);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    public E remove(int index) {
        try {
            URNode<E> node = this.head;
            for (int i=0;i<index;i++) {
                if (node == null) throw new Exception("There is no element at index " + index);
                node = node.next();
            }
            if (node!=null) {
                URNode<E> removedNode = node;
                if (node.next()==null && node.prev()==null) this.head=this.tail=null;
                else if (node.next()==null) {
                    this.tail = node.prev();
                    this.tail.setNext(null);
                }
                else if (node.prev()==null) {
                    this.head = node.next();
                    this.head.setPrev(null);
                }
                else {
                    node.prev().setNext(node.next());
                    node.next().setPrev(node.prev());
                }
                this.size--;
                return removedNode.element();
            }
            else throw new Exception("There is no element at index " + index);
        }
        catch (Exception err) {
            System.err.println(err);
            return null;
        }
    }

    public boolean remove(Object o) {
        URNode<E> oNode = new URNode<>((E) o,null,null);
        try {
            URNode<E> node = this.head;
            while (node!=null) {
                if (node.element()==oNode.element()) {
                    if (node.next()==null && node.prev()==null) this.head = this.tail = null;
                    else if (node.next() == null) {
                        this.tail = node.prev();
                        this.tail.setNext(null);
                    }
                    else if (node.prev() == null) {
                        this.head = node.next();
                        this.head.setPrev(null);
                    }
                    else {
                        node.prev().setNext(node.next());
                        node.next().setPrev(node.prev());
                    }
                    this.size--;
                    return true;
                }
                node = node.next();
            }
            return false;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public boolean removeAll(Collection<?> c) {
        try {
            for (Iterator<E> it = (Iterator<E>) c.iterator(); it.hasNext();) {
                E e = it.next();
                if (!this.remove(e)) {
                    return false;
                }
            }
            return true;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public E set(int index, E element) {
        try {
            URNode<E> newNode = new URNode<>(element,null,null);
            URNode<E> node = this.head;
            for (int i=0;i<index;i++) {
                if (node == null) throw new Exception("There is no element at index " + index);
                node = node.next();
            }
            if (node.prev() == null && node.next() == null) {
                this.head = this.tail = newNode;
            }
            else if (node.prev() == null) {
                this.head = newNode;
                newNode.setNext(node.next());
                node.setPrev(newNode);
            }
            else if (node.next() == null) {
                this.tail = newNode;
                newNode.setPrev(node.prev());
                node.setNext(newNode);
            }
            else {
                node.prev().setNext(newNode);
                newNode.setPrev(node.prev());
                newNode.setNext(node.next());
                node.setPrev(newNode);
            }
            return newNode.element();
        }
        catch (Exception err) {
            System.err.println(err);
            return null;
        }
    }

    public int size(){
        int count = 0;
        URNode<E> node = this.head;
        while (node!=null) {
            count++;
            node = node.next();
        }
        return count;
    }

    public URList<E> subList(int fromIndex, int toIndex){
        URList<E> sub = new URArrayList<>();
        try {
            URNode<E> node = this.head;
            int index = 0;
            while (node != null) {
                if (index >= fromIndex && index< toIndex) sub.add(node.element());
                node = node.next();
                index++;
            }
            return sub;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public Object[] toArray() {
        try {
            int a = this.size();
            E[] e = (E[]) new Object[a];
            URNode<E> node = this.head;
            int index = 0;
            while (node != null) {
                e[index] = node.element();
                node = node.next();
                index++;
            }
            return e;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public void addFirst(E e) {
        this.add(0,e);
    }

    public void addLast(E e) {
        this.add(e);
    }

    public E peekFirst() {
        return this.get(0);
    }

    public E peekLast() {
        return this.get(this.size()-1);
    }

    public E pollFirst() {
        return this.remove(0);
    }

    public E pollLast() {
        return this.remove(this.size()-1);
    }
}