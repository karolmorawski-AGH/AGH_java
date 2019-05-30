package com.agh.lab10.Controller;

import com.agh.lab10.Model.ImageEdition;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static com.agh.lab10.Model.ImageEdition.*;

@RestController
public class ImageReceive {

    //Upload image
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String uploadFile(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {

        InputStream in = new ByteArrayInputStream(file.getBytes());
        BufferedImage img = ImageIO.read(in);

        String id = ImageContainer.addImage(img);

        return "{\"id\" : \"" + id + "\", \"height\": \"" + img.getHeight() + "\", \"width\": \"" + img.getWidth() + "\"}";
    }


    //Get image size
    @GetMapping("/image/{id}/size")
    public String getSize(@PathVariable String id) {

        BufferedImage img = ImageContainer.getImage(id);
        if(img != null){
            return "{height: " + img.getHeight() + ", width: " + img.getWidth() + "}";
        } else {
            return "{404 pożar kurwa pali sie}\n";
        }


    }

    //Get histogram
    @GetMapping("/image/{id}/histogram")
    public String getHistogram(@PathVariable String id) {

        BufferedImage img = ImageContainer.getImage(id);
        if(img != null){
            return Histogram.calculate(img);
        } else {
            return "{404 :)}\n";
        }
    }

    //Delete Image
    @DeleteMapping("/image/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {

        if(!ImageContainer.containsKey(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        BufferedImage img = ImageContainer.getImage(id);
        ImageContainer.removeImage(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //Get image
	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET, produces =
			MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String id) throws Exception {
        BufferedImage img = ImageContainer.getImage(id);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(img, "png", out);
        byte[] imageInByte = out.toByteArray();

        return imageInByte;
    }

    //Greyscale
    @RequestMapping(value = "/image/{id}/grey", method = RequestMethod.GET, produces =
            MediaType.IMAGE_PNG_VALUE)
    public byte[] getGreyscaledImage(@PathVariable String id) throws Exception {
        BufferedImage img = ImageContainer.getImage(id);
        BufferedImage greyimg = convertToGrayScale(img);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(greyimg, "png", out);
        byte[] imageInByte = out.toByteArray();

        return imageInByte;
    }

    //Crop
    @RequestMapping(value = "/image/{id}/crop/{start}/{stop}/{width}/{height}", method = RequestMethod.GET, produces =
            MediaType.IMAGE_PNG_VALUE)
    public byte[] getCroppedImage(@PathVariable String id, @PathVariable int start, @PathVariable int stop, @PathVariable int width, @PathVariable int height ) throws Exception {
        BufferedImage img = ImageContainer.getImage(id);

        Rectangle rectangle = new Rectangle(start, stop, width, height);
        BufferedImage cropped = ImageEdition.cropImage(img, rectangle);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(cropped, "png", out);
        byte[] imageInByte = out.toByteArray();

        return imageInByte;
    }

    //Scale image
    @RequestMapping(value = "/image/{id}/scale/{percentage}", method = RequestMethod.GET, produces =
            MediaType.IMAGE_PNG_VALUE)
    public byte[] getScaledImage(@PathVariable String id, @PathVariable double percentage) throws Exception {

        BufferedImage img = ImageContainer.getImage(id);
        BufferedImage after = scaleBicubic(img, percentage);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(after, "png", out);
        byte[] imageInByte = out.toByteArray();

        return imageInByte;
    }

    //Blur image
    @RequestMapping(value = "/image/{id}/blur/{radius}", method = RequestMethod.GET, produces =
            MediaType.IMAGE_PNG_VALUE)
    public byte[] getBlurredImage(@PathVariable String id, @PathVariable int radius) throws Exception {

        BufferedImage img = ImageContainer.getImage(id);
        BufferedImage after = gaussianBlur(img, radius);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(after, "png", out);
        byte[] imageInByte = out.toByteArray();

        return imageInByte;
    }

}
