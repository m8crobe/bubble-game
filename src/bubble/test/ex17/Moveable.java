package bubble.test.ex17;

public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	// down은 implement해도 구현할 필요가 없게 하기 위해 default값 주기. MoveAdepter 삭제
	// 인터페이스도 default를 사용하면 몸체가 있는 메서드를 만들 수 있음 (다중 상속이 안되는 것이 많기 때문)
	// 어댑터 패턴보다는 default 사용하는 것이 좋음
	default public void down() {};		
	default public void attak() {};		// 버블쏘기		
}
