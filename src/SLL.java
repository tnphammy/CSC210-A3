/**
 * Class to implement a singly linked list
 *
 * @author
 * @version Spring 2024
 */

public class SLL<T> {
    /* ATTRIBUTES */
    /** The first accessible Node of the SLL */
    NodeSL<T> head;
    /** The last accessible Node of the SLL */
    NodeSL<T> tail;

    /* PHASE 1 */

    /* CONSTRUCTOR */
    /**
     * Creates an empty SLL
     * 
     * @param head The first, empty, Node
     */
    public SLL() {
        this.head = null;
        this.tail = this.head; // UNSURE
    }

    public SLL(SLL<T> orig) {
        // Empty list
        if (orig.head == null) {
            head = tail = null;
        } else {
            // Make a temporary node (set as first node)
            NodeSL<T> temp = orig.head;
            head = temp; // Set current SLL's head
            tail = head; // Placeholder tail
            // Traverse the remaining nodes
            for (temp = orig.head.getNext(); temp != null; temp = temp.getNext()) {
                // Attach the current Node (as a new Node) to the end of current tail
                NodeSL<T> currAsNew = new NodeSL<>(temp.getData(), null);
                tail.setNext(currAsNew);
                // Update tail
                tail = currAsNew;
            }

        }
    }

    /**
     * Accessor for head node
     * 
     * @return the head node
     */
    public NodeSL<T> getHead() {
        return this.head;
    }

    /**
     * Accessor for tail node
     * 
     * @return the tail node
     */
    public NodeSL<T> getTail() {
        // Updates the tail
        updateTail();
        // Happy case: SLL has more than one Node
        return this.tail;
    }

    /** Updates the tail of the SLL at the moment it is called */
    public void updateTail() {
        // Edge case: SLL is Empty or has only 1 Node
        if (this.isEmpty() || this.head.getNext() == null) {
            this.tail = this.head;
        }
        // Normal Case: More than 1 element
        NodeSL<T> curr = this.tail;
        // Iterate until the end of the SLL
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
    }

    /**
     * Determines whether a list is empty
     * 
     * @return T/F is the list empty?
     */
    public boolean isEmpty() {
        return (this.head == null);
    }

    /**
     * Inserts the given item at the head of the list
     * 
     * @param v item to insert
     */
    public void addFirst(T v) {
        // Attach v as a Node to the front of the SLL
        NodeSL<T> newNode = new NodeSL<T>(v, head);
        // Set Node to be the new SLL head
        this.head = newNode;
        // Set the SLL tail
        this.tail = this.getTail();
    }

    /** Converts to a string representation */
    public String toString() {
        // Edge case: Empty SLL
        if (this.head == null) {
            return "[]";
        }
        // Edge case: Only one Node
        if (this.head == this.tail) {
            return "[" + this.head.getData() + "]";
        }
        // Happy case: more than one Node
        else if (this.head != null && this.head != this.tail) {
            String output = "[";
            // Make a temp pointer
            NodeSL<T> curr = this.head;
            output += curr.getData(); // Head Node gets added first
            while (curr.getNext() != null) {
                // Update the pointer
                curr = curr.getNext();
                // Add current pointer Node to output
                output += ", " + curr.getData();
            }
            return output + "]";
        }
        return "[]";
    }

    /** Prints the head and tail items */
    public void printFirstAndLast() {
        System.out.println("Head: " + this.getHead().getData());
        System.out.println("Tail: " + this.getTail().getData());
    }

    /* PHASE 2 */
    /**
     * Inserts the given item at the tail of the list
     * 
     * @param item to insert
     */
    public void addLast(T v) {
        // Make the new item into a newTail
        NodeSL<T> newTail = new NodeSL<T>(v, null);
        // Point the tail of the SLL -> newTail
        this.tail.setNext(newTail);
        // Update the tail to be newTail
        this.tail = newTail;
    }

    /**
     * Inserts the given item after the specified node
     * 
     * @param here node to insert after
     * @param v    item to insert
     */
    public void addAfter(NodeSL<T> here, T v) {
        // 1. Find the specified Node (find by data match)
        NodeSL<T> curr = this.head;
        while (curr.getData() != here.getData()) {
            curr = curr.getNext();
        }
        // 2. Store the Node next to it
        NodeSL<T> next = curr.getNext();
        // 3. Make v into a newNode
        NodeSL<T> newNode = new NodeSL<T>(v, next);
        // 4. Adjust pointers accordingly
        curr.setNext(newNode);
    }

    /**
     * Removes the given item from the head of the list
     * 
     * @return v item removed
     */
    public T removeFirst() {
        // Store new head
        NodeSL<T> newHead = this.head.getNext();
        // Make the first item (the head) point to nothing,
        this.head.setNext(null);
        T removedData = this.head.getData(); // Store data to return
        // Reassign the head
        this.head = newHead;
        // Return the data of the removed Node
        return removedData;
    }

    /**
     * Removes the given item from the tail of the list
     * 
     * @return item removed
     */
    public T removeLast() {
        // 1. Store tail's data
        T removedData = this.tail.getData();
        // 2. Point the second to last's Node to null
        // 2.1 Set a pointer to find second to last Node
        NodeSL<T> curr = this.head;
        // 2.2 Loop SLL, stopping at second to last position
        while (curr.getNext().getNext() != null) {
            // Update pointer
            curr = curr.getNext();
        }
        // Done, assign curr as new tail
        curr.setNext(null); // Erase curr's pointer to old tail
        this.tail = curr;
        // Return data as requested
        return removedData;
    }

    /**
     * Removes the node after the given position
     * 
     * @param here marks position to remove after
     * @return item removed
     */
    public T removeAfter(NodeSL<T> here) {
        // 0. Edge cases: Empty list -> Do nothing
        if (this.isEmpty()) {
            return null;
        }
        // 1. Locate specified Nodes (beforeDeleted(curr) - toBeDeleted - afterDeleted)
        NodeSL<T> curr = this.head;
        while (curr.getData() != here.getData()) {
            curr = curr.getNext();
        }
        // If nothing follows curr
        if (curr.getNext() == null) {
            return null; // nothing was removed
        }
        else {
            NodeSL<T> toBeDeleted = curr.getNext();
            T removedData = toBeDeleted.getData();
            // If nothing follows toBeDeleted
            if (toBeDeleted.getNext() == null) {
                toBeDeleted.setNext(null); // Delete toBeDeleted
                // Point curr to the void + make it the tail
                curr.setNext(null); 
                this.tail = curr;
                // Return requested data
                return removedData;
            }
            else {
                NodeSL<T> afterDeleted = toBeDeleted.getNext();
                // 2. Adjust pointers accordingly
                // 2.1 toBeDeleted -> null
                toBeDeleted.setNext(null);
                // 2.2. curr -> afterDeleted
                curr.setNext(afterDeleted);
                // 3. Return requested data
                return removedData;
            }
        }
    }

    /**
     * Returns a count of the number of elements in the list
     * 
     * @return current number of nodes
     */
    public int size() {

        int size = 0; // Default count
        // Edge case: Empty SLL
        if (this.isEmpty()) {
            return 0;
        }
        // Loop through SLL
        NodeSL<T> curr = this.head;
        size++; // count head first
        while(curr.getNext() != null) {
            curr = curr.getNext(); // Increment pointer
            size++; // Incrememt size
        }
        return size;
    }

    public static void main(String[] args) {
        SLL<String> list = new SLL<>();
        System.out.println(list.toString());
        list.addFirst("tammy");
        System.out.println(list.toString());
        list.addFirst("sofia");
        System.out.println(list.toString());
        list.addLast("kana");
        System.out.println(list.toString());
        list.addAfter(new NodeSL<String>("kana", null), "mars");
        System.out.println(list.toString());
        System.out.println("Size: " + list.size());

    }
}
