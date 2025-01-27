package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(double a, double b, double c) {
    public Triangle {
        if ( a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Все стороны треугольника должны быть положительными");
        }
        if ( a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Это не треугольник");
        }
    }

    public static void printTrianglePerimeter(Triangle t) {
        String text = String.format("Периметр триугольника со сторонами %f, %f и %f = %f", t.a, t.b, t.c, t.trianglePerimeter());
        System.out.println(text);
    }

    public static void printTriangleArea(Triangle t){
        String text = String.format("Площадь триугольника со сторонами %f, %f и %f = %f", t.a, t.b, t.c, t.triangleArea());
        System.out.println(text);
    }

    public double triangleArea() {
        double p = (a+b+c)/2;
        double t = p*(p-a)*(p-b)*(p-c);
        return Math.sqrt(t);
    }

    public double trianglePerimeter() {
        return a + b +c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(a, triangle.a) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(c, triangle.c) == 0)
                || (Double.compare(a, triangle.b) == 0 && Double.compare(b, triangle.c) == 0 && Double.compare(c, triangle.a) == 0)
                || (Double.compare(a, triangle.c) == 0 && Double.compare(b, triangle.a) == 0 && Double.compare(c, triangle.b) == 0)
                || (Double.compare(a, triangle.a) == 0 && Double.compare(b, triangle.c) == 0 && Double.compare(c, triangle.b) == 0)
                || (Double.compare(a, triangle.c) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(c, triangle.a) == 0)
                || (Double.compare(a, triangle.b) == 0 && Double.compare(b, triangle.a) == 0 && Double.compare(c, triangle.c) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
