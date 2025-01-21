package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {

    public static void printTrianglePerimeter(double a, double b, double c) {
        String text = String.format("Периметр триугольника со сторонами %f, %f и %f = %f", a, b, c, trianglePerimeter(a, b, c));
        System.out.println(text);
    }

    public static void printTriangleArea(double a, double b, double c){
        String text = String.format("Площадь триугольника со сторонами %f, %f и %f = %f", a, b, c, triangleArea(a, b, c));
        System.out.println(text);
    }

    public static double triangleArea(double a, double b, double c) {
        double p = (a+b+c)/2;
        double t = p*(p-a)*(p-b)*(p-c);
        return Math.sqrt(t);
    }

    public static double trianglePerimeter(double a, double b, double c) {
        return a + b +c;
    }
}
