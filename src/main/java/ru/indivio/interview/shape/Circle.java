package ru.indivio.interview.shape;

public class Circle implements Shape {
    Point center;
    int radius;

    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public void draw() {
        System.out.println("Нарисован круг");
    }
}
