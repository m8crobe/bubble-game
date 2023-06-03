package bubble.test.ex12;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// 백그라운드에서 버블의 움직임을 관찰하는 클래스 **버블이 만들어 질 때마다 생성(각각의 버블)
public class BackgroundBubbleService{		

	private BufferedImage image;
	private Bubble bubble;
	
	public BackgroundBubbleService(Bubble bubble) {
		this.bubble = bubble;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	// 버블이 벽에 충돌할 때
	public boolean leftWall() {
		Color leftColor = new Color(image.getRGB(bubble.getX()-10, bubble.getY()+25));
		if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
			return true;
		}
		return false;
	}
	public boolean rightWall() {
		Color rightColor = new Color(image.getRGB(bubble.getX()+50+15, bubble.getY()+25));
		if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
			return true;
		}
		return false;
	}
	public boolean topWall() {
		Color topColor = new Color(image.getRGB(bubble.getX()+25, bubble.getY()-10));
		if(topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
			return true;
		}
		return false;
	}
}
