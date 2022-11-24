public class Main
{
    public static void main(String[] args)
    {
        LinkedList linkedList = new LinkedList();

        linkedList.append(45);
        linkedList.append(13);
        linkedList.append(19);
        linkedList.append(13);
        linkedList.append(8);
        linkedList.print();

        linkedList.append(33);
        linkedList.print();

        linkedList.remove(13);
        linkedList.print();

        System.out.println(linkedList.search(22));

        System.out.println(linkedList.length());

        System.out.println(linkedList.index(8));

        linkedList.insert(2, 7);
        linkedList.print();

        System.out.println(linkedList.pop());
        linkedList.print();

        System.out.println(linkedList.pop(1));
        linkedList.print();

        System.out.println(linkedList.get(3));

        linkedList.set(0, 100);
        linkedList.print();
    }
}
