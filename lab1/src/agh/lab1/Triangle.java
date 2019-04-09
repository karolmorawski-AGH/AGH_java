package agh.lab1;

public class Triangle extends  Figure implements  Printable{

    final double A, B, C;

    public Triangle(double a, double b, double c) throws FigureException {
        A = a;
        B = b;
        C = c;
        if( (a<=0) || (b<=0) || (c<=0))
            throw new FigureException("Sides of a triangle must be non-zero positive values");
        if((a+b<=c) || (a+c<=b) || (b+c<=c))
            throw new FigureException("Triangle does not satisfy the triangle inequality");
    }

    @Override
    double calculatePerimeter() {
        return A + B + C;
    }

    @Override
    double calculateArea() {
        double p = heronP();
        return Math.sqrt(p*(p-A)*(p-B)*(p-C));
    }

    @Override
    public void print() {
        System.out.println("Type of figure: Triangle\n" + "Side A: " + A + "\n" + "Side B: " + B + "\n" + "Side C: " + C + "\n" + "Perimeter: " + calculatePerimeter() + "\n" + "Area: " + calculateArea());
    }

    @Override
    String getType() {
        return "Triangle";
    }

    double heronP() {
        return (A+B+C)/2;
    }

}
