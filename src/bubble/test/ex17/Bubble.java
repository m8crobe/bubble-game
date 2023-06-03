package bubble.test.ex17;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable{
	
	// 의존성 컴포지션
	private BubbleFrame mContext;
	private Player player;
	private Enemy enemy;
	private BackgroundBubbleService backgroundBubbleService;
	
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
	
	public Bubble(BubbleFrame mContext) {		// bubbleFrame 내 player 존재
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.enemy = mContext.getEnemy();
		initObject();
		initSetting();
	}
	
	private void initObject() {
		
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
		
		backgroundBubbleService = new BackgroundBubbleService(this);
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

	/* 버블은 단방향 이동만 하기 때문에 스레드가 하나만 있어도 됨 */
	
	@Override
	public void left() {
		left = true;
		for(int i=0; i<400; i++) {
			x--;
			setLocation(x, y);
			
			if(backgroundBubbleService.leftWall()) {
				left = false;
				break;
			}

			// (버블 x좌표 - 적의 x좌표)의 절대값이 10미만 (버블과 거리차이 10)
			if((Math.abs(x - enemy.getX()) < 10 ) && 
					(Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50 ))  {				
				System.out.println("맞음!");
				if(enemy.getState() == 0) {
					attak();
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for(int i=0; i<400; i++) {
			x++;
			setLocation(x, y);
			if(backgroundBubbleService.rightWall()) {
				right = false;
				break;
			}
			
			// (버블 x좌표 - 적의 x좌표)의 절대값이 45초과 60미만
			if((Math.abs(x - enemy.getX()) < 10 ) && 
					(Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50 ))  {				
				System.out.println("맞음!");
				if(enemy.getState() == 0) {
					attak();
				}
				break;						// 맞으면 for문을 빠져나옴
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while(up) {
			y--;
			setLocation(x, y);
			if(backgroundBubbleService.topWall()) {
				up = false;
				break;
			}
			
			
			try {
				if(state == 0) {				// 기본 버블
					Thread.sleep(1);
				} else {						// 적을 가둔 버블
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(state == 0) {
			clearBubble();
		}
	}
	
	@Override
	public void attak() {
		state = 1;							// 적군 맞은 상태
		enemy.setState(1);							// 적군 맞은 상태
		setIcon(bubbled);
		mContext.remove(enemy); 			// 적군 메모리에서 사라짐
		mContext.repaint();
	}
	
	private void clearBubble() { 			// 천장에 도착하고 3초 후 소멸
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			mContext.remove(this);  		// 메모리에서 사라짐
			mContext.repaint(); 			// BubbleFrame 다시 그리기
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// 적을 가둔 버블은 자동소멸X 유저와 충돌하면 소멸
	public void clearBubbled() {
		new Thread(()->{
			System.out.println("clearBubbled");
			try {
				up = false;
				setIcon(bomb);
				Thread.sleep(1000);
				mContext.remove(this);
				mContext.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
}
