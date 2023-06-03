package bubble.test.ex00;

import lombok.Getter;
import lombok.Setter;

// Lombok  = getter/setter 자동으로 만들어주는 라이브러리
@Getter
@Setter		
class Dog{
	private String name;
}

public class LombokTest {

	public static void main(String[] args) {
		Dog d = new Dog();
		
		d.setName("강아지");
		
		System.out.println(d.getName());
	}
	
	
}
