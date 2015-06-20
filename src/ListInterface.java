/**
 * Created by Jeannius on 6/20/2015.
 */
public interface ListInterface<T> {

    int size();

    void add(T element);

    boolean contains(T element);

    boolean remove(T element);

    T get(T element);

    String toString();

    void reset();

    T getNext();


}
