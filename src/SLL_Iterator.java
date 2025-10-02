/** Keeps track of position in a linked list */
public class SLL_Iterator<T> implements Phase5SLL_Iterator<T> {
    /* ATTRIBUTES */
    /** The SLL taken */
    protected SLL<T> list;
    /** The Node before the Node just passed */
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
        this.curr = null;
        
    }

    /**
     * Tests whether there are any more
     * 
     * @return T/F is it safe to call next()?
     */
    public boolean hasNext() {
        // 0. Edge case: empty list
        if (list.head == null) {
            return false;
        }
        // At beginning of list, right before the list starts
        if (curr == null && list.head != null) {
            return true;
        }
        return (curr.getNext() != null);
    }

    /**
     * Returns next or throws an exception
     * and advances the iterator
     * 
     * @return the next element
     */
    public T next() {
        if (curr == null && list.head != null) {
            curr = list.head;
            // Get data:
            T data = curr.getData();
            return data;
        }
        else if (list.head == null || !hasNext()) {
            throw new MissingElementException();
        }
        else {
            // Get data
            T data = curr.getNext().getData();
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
        curr.setData(data);
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
            prev = null;
            curr = list.head;
        }
        // Adding to front of a list
        else if (curr == null && list.head != null) {
            // Add to front of list
            list.addFirst(data);
            // Change pointer
            curr = list.head;
        }
        else {
            // Add data after curr
            list.addAfter(curr, data);
            // Update pointer 
            this.next();
        }
    }

    /**
     * Removes the node just passed
     * Cannot be called twice in a row without intervening next()
     */
    public void remove() {
        // 0. Edge case: Removing from an empty list or a null 'just passed'
        if (list.isEmpty() || curr == null) {
            throw new MissingElementException();
        }
        // Removing first element
        else if (prev == null && curr != null) {
            list.removeFirst();
            // Update pointers
            prev = null;
            curr = null;
        }              
        else {
            // Store a new curr ( prev -> newCurr)
            NodeSL<T> newCurr = curr.getNext();
            // Remove the element
            list.removeAfter(prev);
            // Update pointers
            prev.setNext(newCurr);
        }
    }
}
