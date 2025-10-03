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
     */
    public SLL() {
        this.head = null;
        this.tail = this.head; // UNSURE
    }

    /** Makes a deep copy of a SLL */
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
            System.out.println("goattt");
        }
        else {
            // Normal Case: More than 1 element
            NodeSL<T> curr = this.tail;
            // Iterate until the end of the SLL
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            this.tail = curr; // Update tail
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
        updateTail();
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
    public void addLast(T item) {
        // Make the new item into a newTail
        NodeSL<T> newTail = new NodeSL<T>(item, null);
        // Edge case: SLL is Empty
        if (this.isEmpty()) {
            this.head = this.tail = newTail;
        }
        else {
            // Point the tail of the SLL -> newTail
            this.tail.setNext(newTail);
            // Update the tail to be newTail
            this.tail = newTail;
        }
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
        // Update tail
        updateTail();
    }

    /**
     * Removes the given item from the head of the list
     * 
     * @return v item removed
     */
    public T removeFirst() {
        // 0. Edge case: Trying to remove from empty list
        if (this.isEmpty()) {
            throw new MissingElementException();
        }
        // Store new head
        NodeSL<T> newHead = this.head.getNext();
        // Make the first item (the head) point to nothing,
        this.head.setNext(null);
        T removedData = this.head.getData(); // Store data to return
        // Reassign the head
        this.head = newHead;
        updateTail();
        // Return the data of the removed Node
        return removedData;
    }

    /**
     * Removes the given item from the tail of the list
     * 
     * @return item removed
     */
    public T removeLast() {
        // 0. Edge case: Trying to remove from empty list
        if (this.isEmpty()) {
            throw new MissingElementException();
        }
        // Edge case: Only one Node left
        if (this.size() == 1) {
            T removedData = this.head.getData();
            this.head = this.tail = null;
            return removedData;
        }
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
        // 0.1. Edge case: Trying to remove head of non-empty list
        if (here == null && (!this.isEmpty())) {
            // Store new head
            NodeSL<T> newHead = this.head.getNext();
            // Point old head to null
            T removedData = this.head.getData();
            this.head.setNext(null);
            // Set new head 
            this.head = newHead;
            // Return requested data
            return removedData;
        }
        // 0.2. Edge cases: Trying to remove from an empty list or element following tail
        if (this.isEmpty() || this.size() == 1) {
            throw new MissingElementException();
        }
        else {
            // 1. Get toBeDeleted Node, store data
            NodeSL<T> toBeDeleted = here.getNext();
            T removedData = toBeDeleted.getData();
            // 2. Get afterDeleted Node
            NodeSL<T> afterDeleted = toBeDeleted.getNext();
            // 3. Adjust pointers
            // 3.1. here -> afterDeleted
            here.setNext(afterDeleted);
            // 3.2. toBeDeleted -> null
            toBeDeleted.setNext(null);
            // 4. Return data
            return removedData;
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
        while (curr.getNext() != null) {
            curr = curr.getNext(); // Increment pointer
            size++; // Incrememt size
        }
        return size;
    }

    /* PHASE 4 */
    /**
     * Makes a copy of elements from the original list
     * 
     * @param here starting point of copy
     * @param n    number of items to copy
     * @return the copied list
     */
    public SLL<T> subseqByCopy(NodeSL<T> here, int n) {
        // 1. Make copy SLL
        SLL<T> copySLL = new SLL<>();
        // 2.1. Edge case: Empty list -> return early
        if (here == null) {
            return copySLL;
        }
        // 2.2. Edge case: n is larger than the size of the list
        if (this.size() < n) {
            throw new SelfInsertException();
        }
        // 3. Make pointer to iterate through list
        NodeSL<T> curr = here;
        // 4. Loop through list by n -> Copy in new Nodes
        for (int i = 0; i < n; i++) {
            // Add curr's data to copySLL
            copySLL.addLast(curr.getData());
            // Update pointer
            curr = curr.getNext();
        }
        // 5. Return SLL
        return copySLL;
    }

    /**
     * Places copy of the provided list into this after the specified node.
     * 
     * @param list      the list to splice in a copy of
     * @param afterHere marks the position in this where the new list should go
     */
    public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere) {
        // 0. Edge case: Trying to splice an empty list
        if (this.size() == 0) {
            throw new SelfInsertException();
        }
        // 0.1. Edge case: Trying to insert a list into itself
        if (this == list) {
            throw new SelfInsertException();
        }
        // Condition: Only insert if `list` isn't empty
        if (!list.isEmpty()) {
            // 1. Make a copy of the list
            SLL<T> copySLL = list.subseqByCopy(list.getHead(), list.size());
            // 1.1. Edge case: Trying to insert at the front (afterHere == null)
            if (afterHere == null) {
                // Attach the tail of list to the head of current SLL
                copySLL.tail.setNext(this.head);
                // Update the head of current list
                this.head = copySLL.head;
            }
            else {
                // 2. Store a continueHere to attach after the new list
                NodeSL<T> continueHere = afterHere.getNext();
                // 3. Attach new list elements to afterHere
                afterHere.setNext(copySLL.head);
                // 4. Point the end of new list to continueHere
                copySLL.tail.setNext(continueHere);
            }
        }
    }

    /**
     * Extracts a subsequence of nodes and returns them as a new list
     * 
     * @param afterHere marks the node just before the extraction
     * @param toHere    marks the node where the extraction ends
     * @return the new list
     */
    public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere) {
        // 0. Edge cases: this SLL is empty
        if (this.isEmpty()) {
            throw new MissingElementException();
        }
        // 1. Make a new list for extracted elements
        SLL<T> extractedList = new SLL<T>();
        // Condition: Only operate if both args are not null at once
        if (afterHere != null || toHere != null) {
            // Edge case: either argument is null
            if (afterHere == null) {
                // Loop and removeFirst each element until toHere is gone
                NodeSL<T> stop = toHere.getNext();
                while (this.head != stop) {
                    T removedData = this.removeFirst(); // remove from front
                    extractedList.addLast(removedData); // store removed data
                }
            }
            else if (toHere == null) {
                // Loop and removeLast each element until afterHere 
                NodeSL<T> stop = afterHere.getNext();
                while (this.tail != stop) {
                    T removedData = this.removeFirst(); // remove from front
                    extractedList.addLast(removedData); // store removed data
                }
            }
            else {
                // Happy case :)
                // 1. Make an anchor (the element before the first extracted element)
                NodeSL<T> anchor = afterHere;
                // 2. Loop and remove each element after anchor until toHere
                NodeSL<T> stop = toHere.getNext();
                while (anchor.getNext() != stop) {
                    System.out.println("anchor is: " + anchor.getData());
                    T removedData = this.removeAfter(anchor); // remove data
                    extractedList.addLast(removedData); // store removed data
                    System.out.println("extracted: " + extractedList.toString());
                } 
            }
        }
        return extractedList;
    }

    /**
     * Takes the provided list and inserts its elements into this
     * after the specified node. The inserted list ends up empty.
     * 
     * @param list      the list to splice in. Becomes empty after the call
     * @param afterHere Marks the place where the new elements are inserted
     */
    public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere) {
        // 0. Edge case: Trying to splice an empty list
        if (this.size() == 0) {
            throw new SelfInsertException();
        }
        // 0.1. Edge case: Trying to insert a list into itself
        if (this == list) {
            throw new SelfInsertException();
        }
        // Condition: Only insert if `list` isn't empty
        if (!list.isEmpty()) {
            // 0. Edge case: Trying to insert at front of list
            if (afterHere == null) {
                // 1. Loop: Take out the last element of `list` -> Add to front of this SLL
                while (!list.isEmpty()) {
                    // 1.1. Take out (mutating) data of the tail of list
                    T toBeAdded = list.removeLast();
                    // 1.2. Add that tail to front of this SLL
                    this.addFirst(toBeAdded);
                }
            }
            else {
                // Happy case :)
                // 1. Loop: Take out the last element of `list` -> Add at correct index
                while (!list.isEmpty()) {
                    // 1.1. Take out (mutating) data of the tail of list
                    T toBeAdded = list.removeLast();
                    // 1.2. Add that tail after afterHere
                    this.addAfter(afterHere, toBeAdded);
                }
            }
        }
    }
}
