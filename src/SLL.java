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

    /* CONSTRUCTOR */
    /**
     * Creates an empty SLL
     * @param head The first, empty, Node
     */
    public SLL(NodeSL<T> head) {
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
     *  Accessor for head node
     *  @return the head node
     */
    public NodeSL<T> getHead() {
        return this.head;
    }

    /** 
     *  Accessor for tail node
     *  @return the tail node
     */
    public NodeSL<T> getTail() {
        NodeSL<T> defaultNode = new NodeSL<T>(null, null);
        return defaultNode;
    }

    /** 
     *  Determines whether a list is empty
     *  @return T/F is the list empty?
     */
    public boolean isEmpty() {
        // If the head is null
        if (this.head.getData() == null) {
            return true;
        }
        else {
            return false;
        }
    }

    /** 
     *  Inserts the given item at the head of the list
     *  @param v item to insert 
     */
    public void addFirst(T v) {

    }

    /** Converts to a string representation */
    public String toString() {
        // Edge case: Only one Node
        if (this.head == this.tail) {
            return "[" + this.head.getData() + "]";
        }
        // Happy case: more than one Node
        else if (this.head != null && this.head != this.tail) {
            String output = "["; 
            // Make a temp pointer
            NodeSL<T> curr = this.head; 
            output += curr + ", "; // Head Node gets added first
            while (curr != this.tail) {
                // Update the pointer
                curr = curr.getNext();
                // Add current pointer Node to output
                output += curr.getData() + ", ";
            }
            // Add tail to output without following comma
            if (curr == this.tail) {
                output += curr.getData();
            }
            return output + "]";
        }
        // Edge case: Empty SLL
        else {
            return "[]";
        }
    }
 }
