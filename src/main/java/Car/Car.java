package Car;

abstract public class Car { //не обязательно делать abstract
    //public Car.Engine engine; нарушение инкапсуляции изменен на private
    private Engine engine;  //Создан класс Car.Engine
    private String color;
    private String name;

    //добавлен конструктор
    public Car(Engine engine, String color, String name) {
        this.engine = engine;
        this.color = color;
        this.name = name;
    }

    public Engine getEngine() {
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine ;
    }

    //protected void start() заменен на
    public void start() {
        System.out.println("Car.Car starting");
    }
    //abstract void open (); целесообразно перенести в интерфейс
    //так как используется только в Car.LightWeightCar
    //либо оставить и добавить переопределение в Car.Lorry


    public String getColor() {
        return color ;
    }

    public void setColor(String color) {
        this.color = color ;
    }

    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car.Car{" +
                "engine=" + engine +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}