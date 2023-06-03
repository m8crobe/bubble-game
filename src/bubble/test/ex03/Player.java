package bubble.test.ex03;

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

	// 플레이어의 현재 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	public Player() {
		initObject();
		initSetting();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}

	private void initSetting() {
		x = 55;
		y = 535;

		left = false;
		right = false;
		up = false;
		down = false;
		
		this.setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	// --------이벤트 핸들러--------
	// #----- 그림 변경 시점 : 이벤트루프에 모든 task가 완료되어야 repaint됨
	// #----- 메인스레드만 있으면 이벤트루프 전달 가능한 키가 하나뿐임 
	// #----- => 두가지 이벤트를 동시에 처리하기 위해 스레드 적용
	// #----- 키보드를 누를 떄 스레드 생성, 키보드를 뗄 때 스레드 종료
	@Override
	public void left() {
		System.out.println("left 스레드 생성");
		left = true;
		new Thread(() -> {
			while(left) {
				setIcon(playerL);
				x = x-1;
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
		System.out.println("right 스레드 생성");
		right = true;
		new Thread(() -> {
			while(right) {
				setIcon(playerR);
				x = x+1;
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
	public void up() {
		System.out.println("점프");
	}

	@Override
	public void down() {

	}
}
