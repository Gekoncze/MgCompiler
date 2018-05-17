package cz.mg.projectexplorer.components.extensions;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.Icon;


public class BufferedImageIcon implements Icon {
	private final BufferedImage image;

	public BufferedImageIcon(BufferedImage image) {
		this.image = image;
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.drawImage(image, x, y, 64, 64, c);
	}

	@Override
	public int getIconWidth() {
		return 64;
	}

	@Override
	public int getIconHeight() {
		return 64;
	}

	public BufferedImage getImage() {
		return image;
	}
}
