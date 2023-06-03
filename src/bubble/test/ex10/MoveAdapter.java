package bubble.test.ex10;

// 어댑터패턴 사용해보기
// 추상 클래스에서 특정 메서드만 걸러내기 위한 역할
public abstract class MoveAdapter implements Moveable {

	@Override
	public void down() {}
	
}

