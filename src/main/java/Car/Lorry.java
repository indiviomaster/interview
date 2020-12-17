package Car;

// class Lorry extends Car , Moveable , Stopable не может extend больше 1 класса
public class Lorry extends Car implements Moveable,Stopable {
    //добавлен конструктор
    public Lorry(Engine engine, String color, String name) {
        super(engine, color, name);
    }

    public void move (){
        System.out.println("Car is moving");
    }

    public void stop (){
        System.out.println("Car is stop");
    }


}
