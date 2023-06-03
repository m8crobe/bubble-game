package bubble.test.ex10;

public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	public abstract void down();
	// down은 implement해도 구현할 필요가 없게 하기 위해 default값 주기
//	default public void down() {};
}
