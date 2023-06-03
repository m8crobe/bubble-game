package bubble.game.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import bubble.game.component.Enemy;

public class BackgroundEnemyService implements Runnable{

	private BufferedImage image;
	private Enemy enemy;
	
	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public void run() {
		while(enemy.getState()==0) {
			
			// 벽 충돌 체크
			Color leftColor = new Color(image.getRGB(enemy.getX()-10, enemy.getY()+25)); // 플레이어의 크기 50*50
			Color rightColor = new Color(image.getRGB(enemy.getX()+50+15, enemy.getY()+25)); // 플레이어의 크기 50*50
			
			// 바닥의 색상
			int bottomColor = image.getRGB(enemy.getX() + 10, enemy.getY()+50+5)
					+ image.getRGB(enemy.getX()+40, enemy.getY()+50+5); 		// 가장 왼쪽하단과 오른쪽 하단
			
			// 바닥 충돌 확인
			if(bottomColor != -2) {
				enemy.setDown(false);
			} else {
				if(!enemy.isUp() && !enemy.isDown()) {
					enemy.down();
				}
			}
			
			// 벽충돌
			if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				enemy.setLeft(false);
				if(!enemy.isRight()) {
					enemy.right();
				}
			} else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				enemy.setRight(false);
				if(!enemy.isLeft()) {
					enemy.left();
				}
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
