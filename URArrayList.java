
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class URArrayList<E> implements URList<E>, Iterable<E> {
    private E[] arr;
    private int size;
    private int capacity;
    private static final int defaultCapacity = 1;


    public URArrayList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        if (capacity < 0)
            throw new IllegalArgumentException();
        arr = (E[]) new Object[capacity];
    }

    public URArrayList() {
        this.capacity = defaultCapacity;
        this.size = 0;
        if (capacity < 0)
            throw new IllegalArgumentException();
        arr = (E[]) new Object[capacity];
    }

    public boolean add(E e) {
        try {
            if (this.size >= this.capacity)
                //overcapacity
                this.ensureCapacity(size + 1);
            this.arr[this.size] = e;
            this.size++;
            return true;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public void add(int index, E element) {
        try {
            if (this.size >= this.capacity)
                this.ensureCapacity(size + 1);
            for (int i=index; i<this.size; i++) {
                this.arr[i+1]=this.arr[i];
            }
            this.size++;
            this.arr[index] = element;
        }
        catch (Exception err) {
            throw err;
        }
    }


    public boolean addAll(Collection c) {
        try {
            while (this.size + c.size() >= this.capacity) {
                this.ensureCapacity(this.capacity + 1);
            }
            int index = this.size;
            for (Iterator<E> it = (Iterator<E>) c.iterator(); it.hasNext();) {
                Object e = it.next();
                this.arr[index] = (E) e;
                index++;
            }
            this.size += c.size();
            return true;
        }
        catch (Exception err) {
            throw err;
        }
    }


    public boolean addAll(int index, Collection c) {
        try {
            int cLength = c.size();
            while (this.size + cLength > this.capacity) {
                this.ensureCapacity(this.capacity + 1);
            }
            for (int i = this.size - 1; i >= index; i--) {
                if (this.arr[i] != null) {
                    this.arr[i + cLength] = this.arr[i];
                }
            }
            for (Iterator<E> it = (Iterator<E>) c.iterator(); it.hasNext();) {
                E e = it.next();
                this.arr[index] = (E) e;
                index++;
            }
            this.size += c.size();
            return true;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public void clear() {
        for (int i=0;i<this.arr.length;i++) {
            this.arr[i] = null;
        }
        this.size = 0;
    }

    public boolean contains(Object o) {
        for (int i=0; i<this.arr.length;i++) {
            if (this.arr[i].equals(o)) return true;
            else return false;
        }
        return false;
    }

    public boolean containsAll(Collection c) {
        for (Iterator<E> it = (Iterator<E>) c.iterator(); it.hasNext();) {
            Object check = it.next();
            if (!this.contains(check)) return false;
        }
        return true;
    }


    public boolean equals(Object o) {
        URArrayList<E> oList = (URArrayList<E>) o;
        URArrayList<E> copy = new URArrayList<>();
        copy.addAll(Arrays.asList(this.toArray()));
        for (int i=0; i<copy.arr.length; i++) {
            if (copy.get(i)!=null) {
                if (!oList.remove(copy.get(i))) {
                    return false;
                }
            }
        }
        if (oList.size > 0) {
            return false;
        }
        return true;
    }

    public E get(int index) {
        if (index >= this.capacity) throw new ArrayIndexOutOfBoundsException();
        return (E) this.arr[index];
    }


    public int indexOf(Object o) {
        for (int i=0;i<this.size;i++) {
            if (this.arr[i]!=null && this.arr[i].equals(o)) return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public Iterator<E> iterator() {
        Iterator<E> ite = new Iterator<E>() {
            private int current=0;

            @Override
            public boolean hasNext() {
                return current < size && arr[current] != null;
            }
            @Override
            public E next() {
                return arr[current++];
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return ite;
    }

    public E remove(int index) {
        if (index<0) throw new ArrayIndexOutOfBoundsException();
        E e = (E) this.arr[index];
        for (int i=index+1;i<this.arr.length;i++) {
            if (this.arr[i-1] == null) {
                break;
            }
            this.arr[i-1]=this.arr[i];
        }
        this.arr[this.arr.length-1] = null;
        this.size--;
        return e;
    }


    public boolean remove(Object o) {
        try {
            int idx = this.indexOf(o);
            if (idx == -1) return false;
            else {
                this.remove(idx);
                return true;
            }
        }
        catch (Exception err) {
            System.err.print(err);
            return false;
        }
    }

    public boolean removeAll(Collection c) {
        try {
            for (Iterator<E> it = (Iterator<E>) c.iterator(); it.hasNext();) {
                E e = (E) it.next();
                if (!this.remove((E) e)) {
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
        if (index<0 || index >= this.capacity) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if(this.arr[index] == null) {
            this.size++;
        }
        this.arr[index] = element;
        return element;
    }

    public int size() {
        return this.size;
    }

    public URList<E> subList(int fromIndex, int toIndex) {
        if (fromIndex<0 || toIndex>this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        URArrayList<E> sub = new URArrayList<>();
        for (int i=fromIndex;i<toIndex;i++) {
            sub.add((E) this.arr[i]);
        }
        return sub;
    }

    public Object[] toArray() {
        E[] e = (E[]) new Object[this.size];
        for (int i=0;i<this.size;i++) {
            if (this.arr[i]!=null) e[i] = (E) this.arr[i];
        }
        return e;
    }


    void ensureCapacity(int minCapacity) {
        this.capacity = minCapacity; // increase capacity
        E[] arr1 = (E[]) new Object[minCapacity];
        for (int i = 0; i<this.arr.length;i++) {
            arr1[i] = this.arr[i];
        }
        this.arr = arr1;
    }

}