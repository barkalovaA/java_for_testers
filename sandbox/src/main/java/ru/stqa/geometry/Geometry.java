package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(5);
/*        блок реализован через Stream, peek, forEach
            Consumer<Square> print = (square) -> {
            Square.printSquareArea(square);
            Square.printSquarePerimeter(square);
        };*/
        squares.peek(Square::printSquareArea).forEach(Square::printSquarePerimeter);
//        var squares = List.of(new Square(7.0), new Square(5.0), new Square(3.0));
/*  реализовано через функцианальный стиль программирования
            for (Square square : squares) {
            Square.printSquareArea(square);
        }*/
/*  т.к. переменная одна и тело функции состоит из одной строки, то можно преобразовать -> смотреть код ниже
            Consumer<Square> print = (square) -> {
            Square.printSquareArea(square);
        };
        squares.forEach(print);*/
//        squares.forEach(Square::printSquareArea);

/*      Square.printSquareArea(new Square(7.0));
        Square.printSquareArea(new Square(5.0));
        Square.printSquareArea(new Square(3.0));

        Square.printSquarePerimeter(new Square(7.0));
        Square.printSquarePerimeter(new Square(5.0));
        Square.printSquarePerimeter(new Square(3.0));

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);

        Triangle.printTrianglePerimeter(new Triangle(3.0, 4.0, 5.0));
        Triangle.printTriangleArea(new Triangle(3.0,4.0,5.0));*/


    }

}
