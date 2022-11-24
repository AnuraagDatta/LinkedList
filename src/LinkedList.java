public class LinkedList<T>
{
    public static class Node<T> //Holds each item in the list
    {
        private Node<T> next;
        private T data;

        public Node(T data)
        {
            this.data = data;
        }

        public Node<T> getNext()
        {
            return next;
        }

        public void setNext(Node<T> next)
        {
            this.next = next;
        }

        public T getData()
        {
            return data;
        }

        public void setData(T data)
        {
            this.data = data;
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
        private Node<T> current;

        public FindInfo(int index, Node<T> current)
        {
            this.index = index;
            this.current = current;
        }

        public int getIndex()
        {
            return index;
        }

        public Node<T> getCurrent()
        {
            return current;
        }
    }

    private Node<T> head;


    public void append(T item)
    {
        if (isEmpty())
        {//Sets the head of the array to the first item, if the list is empty
            head = new Node<T>(item);
        }
        else
        {//Otherwise, finds the last item in the list, and adds another item to the end
            Node<T> current = head;
            while (current.getNext() != null)
            {
                current = current.getNext();
            }
            current.setNext(new Node<T>(item));
        }
    }

    public void remove(T item) throws IllegalArgumentException
    {
        FindInfo findInfo = find(item);
        Node<T> current = findInfo.getCurrent();
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

    public boolean search(T item)
    {
        return find(item).getIndex() != -1; //Index of -1 returned when item not found
    }

    public int index(T item)
    {
        return find(item).getIndex();
    }

    private FindInfo find(T item)
    {
        int index = 0;
        FindInfo findInfo;
        if (!isEmpty())
        {
            Node<T> current = head;
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
        Node<T> current = head;
        while (current != null)
        {
            current = current.getNext();
            length++;
        }
        return length;
    }

    public void insert(int pos, T item) throws IndexOutOfBoundsException
    {
        if (pos == 0)
        {
            Node<T> rejoin = head;
            head = new Node<T>(item);
            head.setNext(rejoin);
        }
        else if (pos >= 0 && pos < length())
        {
            Node<T> current = getBeforePos(pos);
            Node<T> rejoin = current.getNext();
            current.setNext(new Node<T>(item));
            current.getNext().setNext(rejoin);
        }
        else
        {
            throw new IndexOutOfBoundsException("Cannot find position "+pos+" in list of length "+length()+"!");
        }
    }

    public T pop()
    {
        Node<T> current = head;
        Node<T> previous = null;
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

    public T pop(int pos) throws IndexOutOfBoundsException
    {
        T item;
        if (pos == 0)
        {//Checks if item to be removed is the first item.
            item = head.getData();
            head = head.getNext();
        }
        else if (pos > 0 && pos < length())
        {
            Node<T> current = getBeforePos(pos);
            item = current.getNext().getData();
            Node<T> rejoin = current.getNext().getNext();
            current.setNext(rejoin);
        }
        else
        {
            throw new IndexOutOfBoundsException("Cannot find position "+pos+" in list of length "+length()+"!");
        }
        return item;
    }

    private Node<T> getBeforePos(int pos)
    {
        int currentPos = 0;
        Node<T> current = head;
        while (currentPos < pos - 1)
        {
            current = current.getNext();
            currentPos++;
        }
        return current;
    }

    public T get(int pos) throws IndexOutOfBoundsException
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

    public void set(int pos, T item) throws IndexOutOfBoundsException
    {
        if (pos >= 0 && pos < length())
        {
            Node<T> toReplace = getBeforePos(pos);
            if (pos == 0)
            {
                toReplace.setData(item);
            }
            else
            {
                toReplace.getNext().setData(item);
            }
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
