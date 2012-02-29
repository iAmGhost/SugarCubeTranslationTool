package kr.iamghost.sugarcube.translationtool;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageEditor {
	private String filePath;
	private BufferedImage image;
	private Graphics2D graphic;
	private Font font;
	private float fontSize;
	private Color lastColor;
	
	public ImageEditor() {

	}
	
	public void enableAntiAliasing() {
		graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphic.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}
	
	public void copyArea(int startX, int startY, int width, int height, int destX, int destY) {
		graphic.setComposite(AlphaComposite.Src);
		graphic.copyArea(startX, startY, width, height, destX, destY);
	}	
	
	public ImageEditor newImage(String filePath) {
		ImageEditor newImage = new ImageEditor();
		newImage.loadImage(new File(filePath));
		newImage.setFilePath(filePath);
		
		return newImage;
	}
	
	public ImageEditor newImage(int width, int height) {
		ImageEditor newImage = new ImageEditor();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		newImage.setImage(image);
		
		return newImage;
	}
	
	public void deleteArea(int x, int y, int width, int height) {
		graphic.setBackground(new Color(255, 255, 255, 0));
		graphic.clearRect(x, y, width, height);
	}
	
	public void setFont(String fontFileName, float fontSize) {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/" + fontFileName));
			font = font.deriveFont(fontSize);
			this.fontSize = fontSize;
			graphic.setFont(font);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setColor(int color) {
		lastColor = new Color(color);
		graphic.setColor(lastColor);
	}
	
	public void drawText(String text, int x, int y) {
		FontRenderContext frc = graphic.getFontRenderContext();
		TextLayout tl = new TextLayout(text, font, frc);
		
		Shape outline = tl.getOutline(null);
		Rectangle outlineBounds = outline.getBounds();

		graphic.drawString(text, x, y+outlineBounds.height);
	}
	
	public Rectangle drawTextWithOutline(String text, int fillColor, 
			int x, int y, float weight, boolean clipping) {
		FontRenderContext frc = graphic.getFontRenderContext();
		TextLayout tl = new TextLayout(text, font, frc);
		
		Shape outline = tl.getOutline(null);
		Rectangle outlineBounds = outline.getBounds();
		
		AffineTransform transform = new AffineTransform();
		transform = graphic.getTransform();
		
		transform.translate(x, y+outlineBounds.height);
		graphic.setTransform(transform);
		
		graphic.setStroke(new BasicStroke(weight));
		
		graphic.setClip(outline);
		
		graphic.setColor(new Color(fillColor));
		fillRect(outlineBounds.x, outlineBounds.y, outlineBounds.width, outlineBounds.height);
		graphic.setColor(lastColor);
		graphic.draw(outline);
		
		return outlineBounds;
	}
	
	public void fillRect(int x, int y, int width, int height) {
		graphic.fillRect(x, y, width, height);
		graphic.setClip(null);
	}
	
	public void loadImage(File file) {
		try {
			setImage(ImageIO.read(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void copy(ImageEditor image) {
		BufferedImage target = image.getImage();
		
		graphic.drawImage(target, 0, 0, null);
		
	}
	
	public void save() {
		try {
			ImageIO.write(image, "png", new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		graphic = image.createGraphics();
	}
	
	public Graphics2D getGraphic() {
		return graphic;
	}
	
	public void setGraphic(Graphics2D graphic) {
		this.graphic = graphic;
	}
	
	public float getFontSize() {
		return fontSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
