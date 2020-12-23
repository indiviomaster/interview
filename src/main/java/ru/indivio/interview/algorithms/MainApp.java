package ru.indivio.interview.algorithms;

public class MainApp {


    public static void main(String[] args) {
        /*LList<String> list = new LList<>();
        long time =System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            list.add("data+".concat(String.valueOf(i)));
        }


        System.out.println(System.currentTimeMillis()-time);
        long time2 =System.currentTimeMillis();
        System.out.println(list.get(100));
        System.out.println(System.currentTimeMillis()-time);
        for(int i=0;i<10;i++){
            System.out.println(list.remove("data+".concat(String.valueOf(i))));

        }
        System.out.println(list.size());*/
        AList<String> alist = new AList<>();
        alist.add("A");
        alist.add("B");
        alist.add("C");
        alist.add("D");
        alist.add("E");
        alist.set(0,"L");
        alist.remove(3);
        /*for (int i = 0; i < 4000000; i++) { //установлен предел 400 элементов
            alist.add("Z");
        }*/
        System.out.println(alist.size());
        System.out.println(alist.get(3));
        System.out.println(alist);
    }
}
