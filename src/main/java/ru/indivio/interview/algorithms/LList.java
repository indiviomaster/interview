package ru.indivio.interview.algorithms;


public class LList <L> {

    int size = 0;
    Node<L> firstListElement;
    Node<L> lastListElement;

    public LList() {
    }

    private static class Node<L> {
        Node<L> prev;
        L current;
        Node<L> next;


        Node(Node<L> prev, L current, Node<L> next) {
            this.prev = prev;
            this.current = current;
            this.next = next;
        }
    }

    public int size() {
        return size;
    }

    public boolean add(L element) {
        Node<L> lastElement = this.lastListElement;
        Node<L> addedElement = new Node<>(lastElement, element, null);
        this.lastListElement = addedElement;
        if (lastElement == null){
            this.firstListElement = addedElement;
        }
        else {
            lastElement.next = addedElement;
        }
        size++;
        return true;
    }

    public L get(int index) {

        if (index <= size ) {
            Node<L> element = firstListElement;
            for (int i = 0; i < index; i++)
                element = element.next;
            return element.current;
        }

        // throw new ElementNotFoundException (index)
        return null;
    }


    public boolean remove(Object object) {
        if (object!= null) {
            for (Node<L> element = firstListElement; element != null; element = element.next) {
                if (object.equals(element.current)) {
                    deleteNode(element);
                    return true;
                }
            }
        }
        return false;
    }

    L deleteNode(Node<L> nodeToDel) {

        Node<L> prev = nodeToDel.prev;
        L element = nodeToDel.current;
        Node<L> next = nodeToDel.next;


        if (prev == null) {
            firstListElement = next;
        } else {
            prev.next = next;
            nodeToDel.prev = null;
        }

        if (next == null) {
            lastListElement = prev;
        } else {
            next.prev = prev;
            nodeToDel.next = null;
        }

        nodeToDel.current = null;
        size--;
        return element;
    }

}
