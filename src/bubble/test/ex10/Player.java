package bubble.test.ex10;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends JLabel implements Moveable {
	private int x; // x좌표
	private int y; // y좌표

	private ImageIcon playerR, playerL;			// 오른쪽을 보는 플레이어, 왼쪽을 보는 플레이어

	// 플레이어의 방향(이것에 따라 버블의 방향도 바뀜)
	private PlayerWay playerWay;
	
	// 플레이어의 현재 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	// 플레이어의 벽 충돌 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	// 플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;

	
	public Player() {
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}

	private void initSetting() {
		x = 80;
		y = 535;

		left = false;
		right = false;
		up = false;
		down = false;
		
		leftWallCrash = false;
		rightWallCrash = false;
		
		
		playerWay = PlayerWay.RIGHT;
		
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}
	
	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();
	}

	// --------이벤트 핸들러--------
	// #----- 그림 변경 시점 : 이벤트루프에 모든 task가 완료되어야 repaint됨
	// #----- 메인스레드만 있으면 이벤트루프 전달 가능한 키가 하나뿐임 
	// #----- => 두가지 이벤트를 동시에 처리하기 위해 스레드 적용
	// #----- 키보드를 누를 떄 스레드 생성, 키보드를 뗄 때 스레드 종료
	@Override
	public void left() {
		//System.out.println("left");
		playerWay = PlayerWay.LEFT;
		left = true;
		new Thread(() -> {
			while(left) {
				setIcon(playerL);
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
		playerWay = PlayerWay.RIGHT;
		right = true;
		new Thread(() -> {
			while(right) {
				setIcon(playerR);
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
