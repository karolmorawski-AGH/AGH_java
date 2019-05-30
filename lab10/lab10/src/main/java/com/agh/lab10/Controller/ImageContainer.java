package com.agh.lab10.Controller;

import java.awt.image.BufferedImage;
import java.util.*;

public class ImageContainer {

    static Integer simpleId = 0;
    private static Map<String, BufferedImage> container = new HashMap<>();

    static String addImage(BufferedImage img){

        simpleId++;
        String key = simpleId.toString();

        container.put(key,img);

        return key;
    }

    static BufferedImage getImage(String key){
        return container.get(key);
    }

    static void removeImage(String key){
        container.remove(key);
    }

    static boolean containsKey(String key) {
        return container.containsKey(key);
    }



}
