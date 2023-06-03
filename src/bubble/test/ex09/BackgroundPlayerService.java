package bubble.test.ex09;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// 백그라운드에서 플레이어의 움직임을 관찰하는 클래스
public class BackgroundPlayerService implements Runnable{

	private BufferedImage image;
	private Player player;
	
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
//			image = ImageIO.read(new File("image/test.png"));
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public void run() {
		while(true) {
			// 플레이어의 좌표에 따른 색상 확인
			Color leftColor = new Color(image.getRGB(player.getX()-10, player.getY()+25)); // 플레이어의 크기 50*50
			Color rightColor = new Color(image.getRGB(player.getX()+50+15, player.getY()+25)); // 플레이어의 크기 50*50
			
			// 바닥의 색상
			int bottomColor = image.getRGB(player.getX() + 10, player.getY()+50+5)
					+ image.getRGB(player.getX()+40, player.getY()+50+5); 		// 가장 왼쪽하단과 오른쪽 하단
			
			// 바닥 충돌 확인
			if(bottomColor != -2) {
				player.setDown(false);
			} else {
				if(!player.isUp() && !player.isDown()) {
					player.down();
				}
			}
			
			
			if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
//				System.out.println("왼쪽 벽 충돌");
				player.setLeftWallCrash(true);
				player.setLeft(false);
			} else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
//				System.out.println("오른쪽 벽 충돌");
				player.setRightWallCrash(true);
				player.setRight(false);
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
