package ru.indivio.interview.shape;

public class Rectangle implements Shape {
    Point a;
    Point b;
    Point c;
    Point d;

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    public Point getD() {
        return d;
    }

    public Rectangle(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public void draw() {
        System.out.println("Нарисован квадрат");
    }
}
