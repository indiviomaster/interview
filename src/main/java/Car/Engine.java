package Car;

public class Engine {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                '}';
    }
}
