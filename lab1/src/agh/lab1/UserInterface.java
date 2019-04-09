package agh.lab1;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {

    void start() throws IOException, FigureException {
        Scanner sc  = new Scanner(System.in);
        System.out.println("Select figure and enter required data:\n1. Triangle\n2. Circle\n3. Rectangle\n4. Prism\n5. Exit");
        int select = sc.nextInt();

        while(select < 5) {
            switch (select) {

                case 1:
                    Triangle troj = triangleSelection();
                    troj.print();
                    System.in.read();
                    clearScreen();
                    break;

                case 2:
                    Circle kol = circleSelection();
                    kol.print();
                    System.in.read();
                    clearScreen();
                    break;

                case 3:
                    Square prost = squareSelection();
                    prost.print();
                    System.in.read();
                   clearScreen();
                    break;

                case 4:
                    Prism gran = prismSelection();
                    gran.print();
                    System.in.read();
                    clearScreen();
                    break;
            }
            System.out.println("__________________________________\nSelect figure and enter required data:\n1. Triangle\n2. Circle\n3. Rectangle\n4. Prism\n5. Exit");
            select = sc.nextInt();
        }
    }

    private Triangle triangleSelection() throws FigureException {
        System.out.println("Specify the length of triangle sides: A,B,C: ");
        Scanner sc  = new Scanner(System.in);
        double a,b,c;
        System.out.println("A: ");
        a = sc.nextDouble();
        System.out.println("B: ");
        b = sc.nextDouble();
        System.out.println("C: ");
        c = sc.nextDouble();
        Triangle trojkat = new Triangle(a,b,c);
        return trojkat;
    }

    private Square squareSelection() throws FigureException {
        System.out.println("Specify the length of rectangle sides A, B: ");
        Scanner sc  = new Scanner(System.in);
        double a,b;
        System.out.println("A: ");
        a = sc.nextDouble();
        System.out.println("B: ");
        b = sc.nextDouble();
        Square prostokat = new Square(a, b);
        return prostokat;
    }

    private Circle circleSelection() throws FigureException {
        System.out.println("Specify radius: ");
        Scanner sc = new Scanner(System.in);
        double r;
        System.out.println("Radius: ");
        r = sc.nextDouble();
        Circle kolo = new Circle(r);
        return kolo;
    }

    private Prism prismSelection() throws FigureException {
        System.out.println("Select prism base: \n1. Triangle\n2. Circle\n3. Rectangle");
        Scanner sc = new Scanner(System.in);
        int selection = sc.nextInt();
        double height;
        Figure fig = null;

            switch (selection) {
                case 1:
                    fig = triangleSelection();
                    break;

                case 2:
                    fig = circleSelection();
                    break;

                case 3:
                    fig = squareSelection();
                    break;
            }

        System.out.println("Specify prism height: ");
        height = sc.nextDouble();
        Prism gran = new Prism(fig, height);
        return gran;
    }

    public static void clearScreen()  {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}