package agh.lab1;

public class Prism extends Figure implements  Printable {

    private Figure figure;
    private double height;

    public Prism(Figure f, double h) throws FigureException {
        figure = f;
        height = h;
        if(h<=0)
            throw new FigureException("Height of a prism must be a non-zero positive value");
    }

    double calculateVolume() {
        return figure.calculateArea() * height;
    }

    @Override
    double calculatePerimeter() {
        return 0;
    }

    @Override
    double calculateArea() {
        return (2 * figure.calculateArea()) + (figure.calculatePerimeter() * height);
    }

    @Override
    public void print() {
        System.out.print("Type of figure: Prism\n" +  "Base: " + figure.getType() + "\n" + "Area: " + calculateArea() + "\n" + "Volume: " + calculateVolume());
    }

    @Override
    String getType() {
        return null;
    }
}
