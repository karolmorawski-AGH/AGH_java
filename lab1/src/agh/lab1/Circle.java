package agh.lab1;

public class Circle extends Figure implements  Printable {

    private final double R;

    public Circle(double r) throws FigureException {
        R = r;
        if(r<=0)
            throw new FigureException("Radius must be non-zero positive value");
    }

    @Override
   double calculatePerimeter() {
        return 2* Math.PI * R;
    }

    @Override
   double calculateArea() {
        return Math.PI * R * R;
    }

    @Override
    public void print() {
        System.out.println("Type of figure: Circle\n" + "Radius: " + R + "\n" + "Perimeter: " + calculatePerimeter() + "\n" + "Area: " + calculateArea());
    }

    @Override
    String getType() {
        return "Circle";
    }
}
