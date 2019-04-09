package agh.lab1;

public class Square extends  Figure implements  Printable{

    final double A, B;

    //prostokat
    public Square(double a, double b) throws FigureException {
        A = a;
        B = b;
        if((a<=0) || (b<=0))
            throw new FigureException("Sides of a rectangle must be non-zero positive values");
    }

    //kwadrat
    public Square(double a) {
        A = a;
        B = a;
    }

    @Override
    double calculatePerimeter() {
        return 2*A + 2*B;
    }

    @Override
    double calculateArea() {
        return A*B;
    }

    @Override
    public void print() {
        if(A == B) {
            System.out.println("Type of figure: Square\n" + "Side: " + A + "\n" + "Perimeter: " + calculatePerimeter() + "\n" + "Area: " + calculateArea());
        }
        else {
            System.out.println("Type of figure: Rectangle\n" + "Side A: " + A + "\n" + "Side B: " + B + "\n" + "Perimeter: " + calculatePerimeter() + "\n" + "Area: " + calculateArea());
        }
        }

    @Override
    String getType() {
        return "Rectangle";
    }

    }
