package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 	부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우
 	부모객체의 필드값 처리 방법에 대해
 	
 	1. 부모클래스가 Serializable 인터페이스를 구현하도록 함
 	2. 자식클래스에 writeObject()와 readObject()메서드를 이용해
 		부모 객체의 필드값을 직접 처리할 수 있도록 구현
 */
public class T17NoneSerializableParentTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("d:/D_Other/nonSerializable.bin"));
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		
		oos.writeObject(child); // 직렬화
		
		oos.close();
		
		// ㄴ> 객체 직렬화 => 파일 저장
		//
		
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("d:/D_Other/nonSerializable.bin"));
		Object obj = ois.readObject(); // 역직렬화 => 객체 만들어져서 리턴
		
		Child child2 = (Child)obj;
		System.out.println("parentName : " + child2.getParentName());
		// null => 부모한테 Serializable 하든가(1), 자식 클래스에서 필드값으로 직접 처리(2)
		System.out.println("childName : " + child2.getChildName());
		
		ois.close();
		
	}
}

class Parent {
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}

class Child extends Parent implements Serializable {
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	// 직렬화될 때 자동으로 호출됨(접근제어자가 private이 아니면 자동호출 되지 않음)
	private void writeObject(ObjectOutputStream out) throws IOException {
		
		out.writeUTF(getParentName());
		
		out.defaultWriteObject();
	}
	
	// 역직렬화될 때 자동으로 호출됨(접근제어자가 private이 아니면 자동호출 되지 않음)
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		
		setParentName(in.readUTF()); 
		
		in.defaultReadObject();
	}
}
