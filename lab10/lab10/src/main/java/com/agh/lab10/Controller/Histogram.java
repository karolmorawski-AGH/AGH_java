package com.agh.lab10.Controller;


import javax.imageio.ImageIO;
import javax.validation.constraints.Null;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Histogram {


    static List<Double> red = new ArrayList<Double>(Collections.nCopies(256, 0.));
    static List<Double> green = new ArrayList<Double>(Collections.nCopies(256, 0.));
    static List<Double> blue =new ArrayList<Double>(Collections.nCopies(256, 0.));


    static String calculate(BufferedImage img){

        //get image width and height
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++) {
                int pixel = img.getRGB(i,j);

                int redIndex = (pixel >> 16) & 0xff;
                red.set(redIndex, red.get(redIndex)+1);

                int greenIndex = (pixel >> 8) & 0xff;
                green.set(greenIndex, green.get(greenIndex)+1);

                int blueIndex = (pixel & 0xff);
                blue.set(blueIndex, blue.get(blueIndex)+1);
            }

        }

        Double redMax = Collections.max(red);
        Double greenMax = Collections.max(green);
        Double blueMax = Collections.max(blue);

        for(int i=0; i<256;i++){
            red.set(i,red.get(i)/redMax);
            green.set(i,green.get(i)/greenMax);
            blue.set(i,blue.get(i)/blueMax);

        }

        String json = "{\n\tR:{\n";

        for(int i=0; i<red.size(); i++){
            json += "\t\t" + i + ": " + red.get(i) + ",\n";
        }

        json += "\t},\n\tG:{\n";

        for(int i=0; i<green.size(); i++){
            json += "\t\t" + i + ": " + green.get(i) + ",\n";
        }

        json += "\t},\n\tB:{\n";

        for(int i=0; i<blue.size(); i++){
            json += "\t\t" + i + ": " + blue.get(i) + ",\n";
        }

        json += "\n\t}\n}";

        return json;

    }

}
