public class LinkedList
{
    public class Node //Holds each item in the list
    {
        private Node next;
        private int data;

        public Node(int data)
        {
            this.data = data;
        }

        public Node getNext()
        {
            return next;
        }

        public void setNext(Node next)
        {
            this.next = next;
        }

        public int getData()
        {
            return data;
        }

        public void print()
        {
            System.out.print(data+", ");
            if (next != null)
            {
                next.print();
            }
        }
    }

    //Holds the information from the find function, to be used by the remove(), search(), and index() functions
    public class FindInfo
    {
        private int index;
        private Node current;

        public FindInfo(int index, Node current)
        {
            this.index = index;
            this.current = current;
        }

        public int getIndex()
        {
            return index;
        }

        public Node getCurrent()
        {
            return current;
        }
    }

    private Node head;


    public void append(int item)
    {
        if (isEmpty())
        {//Sets the head of the array to the first item, if the list is empty
            head = new Node(item);
        }
        else
        {//Otherwise, finds the last item in the list, and adds another item to the end
            Node current = head;
            while (current.getNext() != null)
            {
                current = current.getNext();
            }
            current.setNext(new Node(item));
        }
    }

    public void remove(int item) throws IllegalArgumentException
    {
        FindInfo findInfo = find(item);
        Node current = findInfo.getCurrent();
        if (findInfo.getIndex() == 0 && current == null)
        {//Checks if it is the first item in the list
            head = head.getNext();
        }
        else
        {
            if (current != null)
            {//If item is found, then skip over the item to be removed when setting the pointers
                current.setNext(current.getNext().getNext());
            }
            else
            {//Throws exception if item not found
                throw new IllegalArgumentException("Item not in list!");
            }
        }
    }

    public boolean search(int item)
    {
        return find(item).getIndex() != -1; //Index of -1 returned when item not found
    }

    public int index(int item)
    {
        return find(item).getIndex();
    }

    private FindInfo find(int item)
    {
        int index = 0;
        FindInfo findInfo;
        if (!isEmpty())
        {
            Node current = head;
            if (current.getData() == item)
            {//Checks if the first item is the one to be found
                findInfo = new FindInfo(0, null);
            }
            else
            {
                while (current.getNext() != null && current.getNext().getData() != item)
                {//Continues until 1 before the end of the list or 1 before the matching item
                    current = current.getNext();
                    index++;
                }
                if (current.getNext() != null)
                {
                    findInfo = new FindInfo(index+1, current);
                }
                else
                {
                    findInfo = new FindInfo(-1, null);
                }
            }
        }
        else
        { //Item cannot be found if list is empty.
            findInfo = new FindInfo(-1, null);
        }
        return findInfo;
    }

    public int length()
    {
        int length = 0;
        Node current = head;
        while (current != null)
        {
            current = current.getNext();
            length++;
        }
        return length;
    }

    public void insert(int pos, int item) throws IndexOutOfBoundsException
    {
        if (pos == 0)
        {
            Node rejoin = head;
            head = new Node(item);
            head.setNext(rejoin);
        }
        else if (pos >= 0 && pos < length())
        {
            Node current = getBeforePos(pos);
            Node rejoin = current.getNext();
            current.setNext(new Node(item));
            current.getNext().setNext(rejoin);
        }
        else
        {
            throw new IndexOutOfBoundsException("Cannot find position "+pos+" in list of length "+length()+"!");
        }
    }

    public int pop()
    {
        Node current = head;
        Node previous = null;
        while (current.getNext() != null)
        {
            previous = current;
            current = current.getNext();
        }
        if (previous != null)
        {
            previous.setNext(null);
        }
        else
        {
            head = null;
        }
        return current.getData();
    }

    public int pop(int pos) throws IndexOutOfBoundsException
    {
        int item;
        if (pos == 0)
        {//Checks if item to be removed is the first item.
            item = head.getData();
            head = head.getNext();
        }
        else if (pos >= 0 && pos < length())
        {
            Node current = getBeforePos(pos);
            item = current.getNext().getData();
            Node rejoin = current.getNext().getNext();
            current.setNext(rejoin);
        }
        else
        {
            throw new IndexOutOfBoundsException("Cannot find position "+pos+" in list of length "+length()+"!");
        }
        return item;
    }

    private Node getBeforePos(int pos)
    {
        int currentPos = 0;
        Node current = head;
        while (currentPos < pos - 1)
        {
            current = current.getNext();
            currentPos++;
        }
        return current;
    }

    public int get(int pos) throws IndexOutOfBoundsException
    {
        if (pos >= 0 && pos < length())
        {
            return (pos == 0) ? head.getData() : getBeforePos(pos).getNext().getData();
        }
        else
        {
            throw new IndexOutOfBoundsException("Cannot find position "+pos+" in list of length "+length()+"!");
        }
    }

    public void set(int pos, int item) throws IndexOutOfBoundsException
    {
        if (pos >= 0 && pos < length())
        {
            insert(pos, item);
            pop(pos + 1);
        }
        else
        {
            throw new IndexOutOfBoundsException("Cannot find position "+pos+" in list of length "+length()+"!");
        }
    }

    public boolean isEmpty()
    {
        return head == null;
    }

    public void print()
    {
        System.out.print("[");
        if (head != null)
        {
            head.print();
        }
        System.out.println("]");
    }
}
