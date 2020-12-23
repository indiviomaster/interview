package ru.indivio.interview.algorithms;

import java.util.Arrays;

public class AList <E> {

    Object[] array;
    private int size;

    public AList(int Cap) {
        if (Cap > 0) {
            this.array = new Object[Cap];
        } else if (Cap == 0) {
            this.array = new Object[5];
        } else {
            throw new IllegalArgumentException("Не правильная емкость массива: " + Cap);
        }
    }


    public AList() {
        this.array = new Object[5];
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {return size == 0;
    }

    public boolean add(E e) {

        if(array.length < (size+1)){
            sizeUp(5);
        }
        array[size++] = e;
        return true;
    }

    private void sizeUp(int addSize) {

        int oldSize = array.length;
        int newSize = oldSize + addSize;
        if (newSize - 5 < 0)
            newSize = 5;
        if (newSize>400)
            throw new ArrayIndexOutOfBoundsException("Достигнут предел количества элементов = 400 требуется"+newSize);
        array = Arrays.copyOf(array, newSize);
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    public E get(int id) {
        if ((id < size)&&(id>=0)) {
            return (E) array[id];
        }
        throw new ArrayIndexOutOfBoundsException("элемента с таким индексом ID = "+id+" не существует");
    }

    public void set(int id, E e) {
        if ((id < size)&&(id>=0)) {
            array[id] = e;
        return;
        }
        throw new ArrayIndexOutOfBoundsException("элемента с таким индексом ID = "+id+" не существует");
    }

    public void remove(int id) {
        if ((id < size)&&(id>=0)) {
            int numMoved = size - id - 1;
            if (numMoved > 0)
                System.arraycopy(array, id + 1, array, id,
                        numMoved);
            array[--size] = null;
            return;
        }
        throw new ArrayIndexOutOfBoundsException("элемента с таким индексом ID = "+id+" не существует");

    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append("AList{");
        for(int i=0; i<size;i++){
            st.append(array[i]).append(",");
        }
        return st.append('}').toString();

    }
}