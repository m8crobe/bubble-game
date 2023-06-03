package bubble.test.ex02;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame{

	private JLabel backgroundMap;				// 배경화면
	private Player player;						// 플레이어
	
	
	public BubbleFrame() {
		initObject();
		initSetting();
		setVisible(true);
	}
	
	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		
		player = new Player();
		add(player);
		//backgroundMap.setSize(1000, 600);
		//backgroundMap.setLocation(300, 300);
		//add(backgroundMap);					// JFrame에 JLabel 그리기
	}
	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); 				// absolute 레이아웃
		setLocationRelativeTo(null);	// 가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 창 닫으면 JVM도 같이 종료
	}
	
	public static void main(String[] args) {
		new BubbleFrame();
	}

}
