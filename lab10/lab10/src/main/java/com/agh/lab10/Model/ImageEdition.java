package com.agh.lab10.Model;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.IOException;

public class ImageEdition {

	//Greyscale
	public static BufferedImage convertToGrayScale(BufferedImage image) {
		BufferedImage result = new BufferedImage(
				image.getWidth(),
				image.getHeight(),
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = result.getGraphics();
		g.drawImage(image, 0, 0, null);


		g.setFont(g.getFont().deriveFont(12f));
		g.drawString("Na zawsze w naszej pamięci [*]",  result.getWidth()/2 -  80, result.getHeight() - 25);
		g.dispose();
		return result;
	}



	//Scale bicubic
	public static BufferedImage scaleBicubic(BufferedImage img, double scale) {

		int w = (int)(img.getWidth() * scale);
		int h = (int)(img.getWidth() * scale);

		BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();

		at.scale(scale, scale);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		after = scaleOp.filter(img, after);

		return after;
	}

	//Crop image
	public static BufferedImage cropImage(BufferedImage img, Rectangle rect) {
		BufferedImage dest = img.getSubimage(rect.x, rect.y, rect.width, rect.height);
		return dest;
	}

	//Blur image
	public static BufferedImage gaussianBlur(BufferedImage img, int radius) {

		int size = radius * 2 + 1;
		float weight = 1.0f / (size * size);
		float[] data = new float[size * size];

		for (int i = 0; i < data.length; i++) {
			data[i] = weight;
		}

		Kernel kernel = new Kernel(size, size, data);
		ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		//tbi is BufferedImage
		BufferedImage i = op.filter(img, null);

		return i;
	}



}
