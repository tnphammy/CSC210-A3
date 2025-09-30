/** Keeps track of position in a linked list */
public class SLL_Iterator<T> implements Phase5SLL_Iterator<T> {
    /* ATTRIBUTES */
    /** The SLL taken */
    protected SLL<T> list;
    /** The Node just passed */
    protected NodeSL<T> prev;
    /** The Node just passed */
    protected NodeSL<T> curr;


    /**
     * Creates a new iterator on the given list.
     * Default position is leftmost
     * 
     * @param list the list to iterate on
     */
    public SLL_Iterator(SLL<T> list) {
        this.list = list;
        this.prev = null; // start at the void
        this.curr = list.head;
    }

    /**
     * Tests whether there are any more
     * 
     * @return T/F is it safe to call next()?
     */
    public boolean hasNext() {
        return (curr != null);
    }

    /**
     * Returns next or throws an exception
     * and advances the iterator
     * 
     * @return the next element
     */
    public T next() {
        if (!hasNext()) {
            throw new MissingElementException();
        }
        else {
            // Get data
            T data = curr.getData();
            // Update pointers
            prev = curr;
            curr = curr.getNext();
            // Return data
            return data;
        }
    }

    /**
     * Sets the data for the element just passed
     * 
     * @param data value to set
     */
    public void set(T data) {
        // 0. Edge case: Setting data in an empty list
        if (list.isEmpty()) {
            throw new MissingElementException();
        }
        // Add data after prev
        prev.setData(data);
    }

    /**
     * Returns the data for the element just passed
     * 
     * @return data value in the element just passed
     */
    public T get() {
        if (curr == null) {
            throw new MissingElementException();
        }
        else {
            return curr.getData();
        }
    }

    /**
     * Inserts a node with the specified data
     * Cannot be called twice in a row without intervening next()
     * 
     * @param data the value to insert
     */
    public void add(T data) {
        // 0. Edge case: Adding data to an empty list
        if (list.isEmpty()) {
            // Add to SLL
            list.addLast(data);
            // Update Iterators pointer
            prev = list.head;
            curr = null;
        }
        else {
            // Add data after prev
            NodeSL<T> newNode = new NodeSL<T>(data, curr);
            // Add to front of non-empty list
            if (prev != null) {
                prev.setNext(newNode);
            }
            else {
                list.head = newNode; // Note: Remember to think about the SLL too
            }
            // Update pointer and list head
            prev = newNode;
        }
    }

    /**
     * Removes the node just passed
     * Cannot be called twice in a row without intervening next()
     */
    public void remove() {
        // 0. Edge case: Removing from an empty list or a null prev
        if (list.isEmpty() || prev == null) {
            throw new MissingElementException();
        }
        else {
            // Update pointer and list head
     
        }
    }
}
