package bubble.test.ex09;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel{
	
	// 의존성 컴포지션
	private Player player;
	
	// 버블의 위치
	private int x; // x좌표
	private int y; // y좌표


	// 버블의 현재 상태
	private boolean left;
	private boolean right;
	private boolean up;
	
	// 적이 맞은 상태인지
	private int state;							// 0(버블), 1(적을 가둔 버블)
	private ImageIcon bubble;					// 버블
	private ImageIcon bubbled;					// 적을 가둔 버블
	private ImageIcon bomb;						// 터지는 버블
	
	public Bubble(Player player) {
		this.player = player;
		initObject();
		initSetting();
	}
	
	private void initObject() {
		
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
	}
	
	private void initSetting() {
		left = false;
		right = false;
		up = false;
		
		x = player.getX();
		y = player.getY();
		
		setIcon(bubble);
		setSize(50, 50);
		state = 0;
	}
	
}
