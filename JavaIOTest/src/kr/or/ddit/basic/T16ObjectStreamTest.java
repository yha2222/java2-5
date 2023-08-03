package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 객체 입출력 보조 스트림(직렬화와 역직렬화) - 객체를 IO 작업
// 직렬화 : 연속적 흐름으로 늘어놓괴 바이트 기반으로 writeObject -> 객체 저장 => 필요할 때 읽기 - object 
// 역직렬화 : 데이터 읽어서 원래 객체 형태 복원
public class T16ObjectStreamTest {

	public static void main(String[] args) {
		
		// Member 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "경기");
		Member mem3 = new Member("이몽룡", 40, "강원");
		Member mem4 = new Member("성춘향", 50, "광주");
		
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(
					new FileOutputStream("d:/D_Other/memObj.bin")); // 실제 IO => 기반 스트림
			// 쓰기 작업
			oos.writeObject(mem1);  // 직렬화
			oos.writeObject(mem2);  
			oos.writeObject(mem3);  
			oos.writeObject(mem4);  
			// 객체를 파일에 저장 끋
			System.out.println("객체 쓰기 작업 완료!"); 
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//
		
		ObjectInputStream ois = null;
		
		try {
			
			ois = new ObjectInputStream(
					new FileInputStream("d:/D_Other/memObj.bin"));
			
			Object obj = null;
			
			// 역직렬화
			while((obj = ois.readObject()) != null) {
				
				// 읽어온 데이터를 원래 객체형으로 변환 후 사용
				Member mem = (Member) obj;
				System.out.println("이름: " + mem.getName());
				System.out.println("나이: " + mem.getAge());
				System.out.println("주소: " + mem.getAddr());
				System.out.println("----------------------------");
			}
			
		} catch (IOException ex) {
			//ex.printStackTrace();
			System.out.println("출력 완료");
		} catch (ClassNotFoundException e) {   //00?????
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

// 회원정보를 담기 위한 VO
class Member implements Serializable {
	// 자바는 Serializable 인터페이스를 구현한 객체만 직렬화할 수 있도록 제한하고 있음
	//       ㄴ추상메서드 없어도 타입은 갖게 됨, IO 작업 가능한 대상임을 표시 => 표식(태그) 인터페이스
	
	// transient => 직렬화가 되지 않을 멤버변수에 지정
	//				직렬화가 되지 않는 멤버변수는 기본값으로 저장됨
	//				(참조변수 : null, 숫자형변수 : 0)
	//				(*static 필드도 직렬화가 되지 않는다)
	
	transient private String name;
	transient private int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}