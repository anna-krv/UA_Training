import java.util.AbstractList;

public class AdvancedList<E> extends AbstractList<E> {
    private static final int INIT_CAPACITY = 20;
    private static final double CAPACITY_GROWTH = 1.5;
    private static final double BUFFER = 0.3;
    private int size = 0;
    private int capacity;
    private Object arr[];

    public AdvancedList() {
        arr = new Object[INIT_CAPACITY];
        capacity = INIT_CAPACITY;
    }

    @Override
    public void add(int index, E elem) {
        if (index != size) {
            throw new UnsupportedOperationException();
        }
        arr[size++] = elem;
        if ((double) size / capacity > 1 - BUFFER) {
            resize();
        }
    }

    private void resize() {
        Object[] copy = arr;
        capacity = (int) (capacity * CAPACITY_GROWTH);
        arr = new Object[capacity];
        for (int i = 0; i < size; i++) {
            arr[i] = copy[i];
        }
    }

    @Override
    public E get(int i) {
        if (i < size) {
            return (E) arr[i];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }
}
