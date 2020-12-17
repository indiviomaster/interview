package Shape;

public class Triangle implements Shape {
    Point a;
    Point b;
    Point c;

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void draw() {
        System.out.println("Нарисован треугольник");
    }
}
