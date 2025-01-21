package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.0));
        Square.printSquareArea(new Square(5.0));
        Square.printSquareArea(new Square(3.0));

        Square.printSquarePerimeter(new Square(7.0));
        Square.printSquarePerimeter(new Square(5.0));
        Square.printSquarePerimeter(new Square(3.0));

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);

        Triangle.printTrianglePerimeter(3, 4, 5);
        Triangle.printTriangleArea(3.0,4.0,5.0);
    }

}
