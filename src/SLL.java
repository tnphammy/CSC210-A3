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
        // Edge case: SLL is Empty or has only 1 Node
        if (this.isEmpty() || this.head.getNext() == null) {
            this.tail = this.head;
            return this.tail;
        }
        // Happy case: SLL has more than one Node
        return this.tail;
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
            while (curr != this.tail) {
                // Update the pointer
                curr = curr.getNext();
                // Add current pointer Node to output
                output += ", " + curr.getData();
            }
            return output + "]";
        }
            return "[]";
    }

    public static void main(String[] args) {
        SLL<String> list = new SLL<>();
        System.out.println(list.toString());
        list.addFirst("tammy");
        System.out.println(list.toString());
        list.addFirst("sofia");
        System.out.println(list.toString());
    }
 }

