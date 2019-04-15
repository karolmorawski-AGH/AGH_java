package calc.model;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;

import static model.Equation.calc;

public class DrawFunction extends Task {

	private GraphicsContext gc;

	public DrawFunction(GraphicsContext gc) {
		this.gc = gc;
	}

	//TODO Occasional  Unrecognized PGCanvas token: -1 and java.nio.BufferOverflowException

	@Override
	protected Object call() throws Exception {

		System.out.println("Starting drawing...");

			//pixel writer
			PixelWriter pw = gc.getPixelWriter();

			BufferedImage bi = new BufferedImage(400,400, BufferedImage.TYPE_INT_RGB);

			//position on canvas
			int xc = 0;
			int yc = 0;

			//real values
			double x = 0 - 8.04;
			double y = 0 - 0.04;

			//Drawing pixels

				for (int i = 0; i <= 400; i++) {
					//if cancelled
					if(this.isCancelled()) {
					break;
					}

					x = x + 0.04;
					y = 0.0 - 8.04;
					for (int j = 0; j <= 400; j++) {
						y = y + 0.04;
						xc = (int) ((x + 8) * 25);
						yc = -(int) (y * 25);
						if (calc(x, y)) {
							//pw.setColor(xc + 0, yc + 200, Color.YELLOW);
							bi.setRGB(xc + 0, yc + 200, 16776960);
						}
						else {
							bi.setRGB(xc + 0, yc + 200, 50);
						}
					}
					gc.drawImage(SwingFXUtils.toFXImage(bi,null), 0, 0);
				}
			System.out.println("Drawing done");

		return null;
	}
}
