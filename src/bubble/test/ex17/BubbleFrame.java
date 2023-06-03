package bubble.test.ex17;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BubbleFrame extends JFrame{

	private BubbleFrame mContext = this;
	private JLabel backgroundMap;				// 배경화면
	private Player player;						// 플레이어
	private Enemy enemy;
	
	
	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);
	}
	
	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		player = new Player(mContext);
		add(player);
		enemy = new Enemy(mContext);
		add(enemy);
		new BGM();
	}
	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); 				// absolute 레이아웃
		setLocationRelativeTo(null);	// 가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 창 닫으면 JVM도 같이 종료
	}
	
	private void initListener() {
		// 전부 구현할 필요가 없기 때문에 어댑터 패턴 사용
		addKeyListener(new KeyAdapter() {
			
 			// 키보드 누를 때 이벤트 핸들러
			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println(e.getExtendedKeyCode());
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT :			// 각 키코드 대신 keyEvent 사용해서 가독성 높이기
					if(!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT :
					if(!player.isRight() && !player.isRightWallCrash()) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP :
					if(!player.isUp() && !player.isDown()) {
						player.up();
					}
					break;
				case KeyEvent.VK_SPACE :
					player.attak();
					break;
				}
			}
			// 키보드 해제 이벤트 핸들러
			@Override
			public void keyReleased(KeyEvent e) { 
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				}
			}
			
		
		});
	}
	
	public static void main(String[] args) {
		new BubbleFrame();
	}

}
