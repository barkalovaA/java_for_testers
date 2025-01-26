package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {

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
}
