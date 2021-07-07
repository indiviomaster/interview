package ru.indivio.interview.car;

public class LightWeightCar extends Car implements Moveable, Openable {

    public LightWeightCar(Engine engine, String color, String name) {
        super(engine, color, name);
    }
    //@Override необходим если оставим abstract open() в Car
    public void open(){
        System.out.println ("Car is open");
    }
    //@Override не требуется
    public void move () {
        System.out.println ("Car is moving");
    }
}