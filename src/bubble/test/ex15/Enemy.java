package bubble.test.ex15;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy extends JLabel implements Moveable {
	
	private BubbleFrame mContext;
	
	
	private int x; // x좌표
	private int y; // y좌표

	private ImageIcon enemyR, enemyL;

	// 적의 방향
	private EnemyWay enemyWay;
	
	// 적의 현재 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	// 적의 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;

	
	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		//initBackgroundPlayerService();
	}

	private void initObject() {
		enemyR = new ImageIcon("image/enemyR.png");
		enemyL = new ImageIcon("image/enemyL.png");
	}

	private void initSetting() {
		x = 480;
		y = 178;

		left = false;
		right = false;
		up = false;
		down = false;
		
		enemyWay = EnemyWay.RIGHT;
		
		setIcon(enemyR);
		setSize(50, 50);
		setLocation(x, y);
	}
	
	private void initBackgroundEnemyService() {
		//new Thread(new BackgroundEnemyService(this)).start();
	}
	


	// --------이벤트 핸들러--------
	@Override
	public void left() {
		//System.out.println("left");
		enemyWay = EnemyWay.LEFT;
		left = true;
		new Thread(() -> {
			while(left) {
				setIcon(enemyL);
				x = x-SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10);	// 0.01초 간격으로 실행
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void right() {
		//System.out.println("right");
		enemyWay = EnemyWay.RIGHT;
		right = true;
		new Thread(() -> {
			while(right) {
				setIcon(enemyR);
				x = x+SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10);	// 0.01초 간격으로 실행
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}

	// left+up, right+up 가능해야함 => 스레드 필수
	@Override
	public void up() {
		//System.out.println("up");
		up = true;

		new Thread(()->{
			for(int i=0;i<130/JUMPSPEED;i++) {	// 점프 높이를 유지 (1기준 130)
				y = y-JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			up = false;
			down();
		}).start();
	}

	@Override
	public void down() {
		
		System.out.println("down");
		down = true;
		new Thread(()->{
			while(down) {		// 바닥 착지를 위해 while로 변경. 기존 for문은 무조건 130만큼 진행되기 때문
				y = y+JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	}
}
