package prac;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class R0807 {

	/*
	 * 1.프로그램 종료시 데이터 저장 - 메소드 saveData() 만들자~ 2.프로그램 실행시 데이터 불러오기 - 메소드 loadData()
	 * 만들자~
	 */

	private Scanner scan;
	private Map<String, HotelVO> hotelBook;

	public void displayMenu() {
		System.out.println("*******************************************\r\n" + "어떤 업무를 하시겠습니까?\r\n"
				+ "1.체크인  2.체크아웃 3.객실상태 4.업무종료\r\n" + "*******************************************");
	}

	public R0807() {
		scan = new Scanner(System.in);
		hotelBook = new HashMap<String, HotelVO>();
		loadInfo();
	}

	public void hotelStart() {
		System.out.println("**************************\r\n" + "호텔 문을 열었습니다.\r\n" + "**************************");

		while (true) {
			displayMenu();

			int menuNum = scan.nextInt();

			switch (menuNum) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomCon();
				break;
			case 4:
				System.out
						.println("**************************\r\n" + "호텔 문을 닫았습니다.\r\n" + "**************************");
				saveInfo();
				return;
			default:
				System.out.println("잘못된 입력!");
				break;
			}
		}
	}

	private void checkIn() {
		scan = new Scanner(System.in);

		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.println("방번호 입력 => ");
		String roomNum = scan.nextLine();

		if (hotelBook.get(roomNum) != null) {
			System.out.println(roomNum + "에는 이미 사람이 있습니다.");
			return;
		}

		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.println("이름 입력 => ");
		String name = scan.nextLine();

		hotelBook.put(roomNum, new HotelVO(roomNum, name));
		System.out.println("체크인 되었습니다.");

	}

	private void checkOut() {
		scan = new Scanner(System.in);

		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.println("방번호 입력 => ");
		String roomNum = scan.nextLine();

		if (hotelBook.remove(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
		} else {
			System.out.println("체크아웃 되었습니다.");
		}

		System.out.println("체크아웃 되었습니다.");
	}

	private void roomCon() {
		Set<String> keySet = hotelBook.keySet();

		if (keySet.size() == 0) {
			System.out.println("투숙객 없음");
		} else {

			int cnt = 0;

			for (String rN : keySet) {
				cnt++;
				HotelVO name = hotelBook.get(rN);

				System.out.println("방번호 : " + rN + "투숙객 : " + name.getName());
			}
		}
	}

	public void saveInfo() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream("d:/D_Other/homework.txt");
			oos = new ObjectOutputStream(fos);
			
//			Map<String, HotelVO>
			Set<String> keySet = hotelBook.keySet();
			for(String keys : keySet) {
				oos.writeObject(hotelBook.get(keys));
			}
			
			/*oos.write(); 여기에 값 넣어줄거야~ 뭐를 넣어줄꺼야? 직렬화된 HotelVO클래스의 객체들 넣어줄게야
			아니야 직렬화는 뭐냐면
			1. 직렬화 시키고 싶은 클래스에 implements Serializable을 붙인다
			2. 이렇게 붙여버리면 직렬화가된다
			3. 직렬화는 뭐냐면 오브젝트를 특정
			*/
			
			// map에있는 kay value 뽑아와서 메모장에 넣고싶은거잖아?
			// map에 key value 어케뽑아와? 그때 쌤이 4개 방법 알려줬잖아 1.keyset 2.entry 3.iterator 4.?
//			fos.write();
			// 예외가 발생할 수 있는 코드 넣어노ㅓㅏ
			// 예를들어
//			예외 = 클래스
//					근데 일단 1. 예외는 클래스
//					2. 예외는 뭐냐? 자바에 오류가 나(보통 내책임90% 1.컴파일단계에서 발견되는 오류  2.실행도중 발생하는 예외, 컴파일단계에서 잡지 못함  arrayindex nullpoint)
//							SQLException e
		} catch (NullPointerException e) {
			System.out.println("dd");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	public void loadInfo() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/homework.txt");
			ois = new ObjectInputStream(fis);
			
			Object obj = null;
			HotelVO hotelVO = null;
			
			while((obj = ois.readObject()) != null){
				hotelVO = (HotelVO)obj;
				// map.put(key, values);
				// 우리지금 map의 키랑 밸류 뭐야? 키=String 밸류=HotelVO타입
				hotelBook.put(hotelVO.getRoomNo(), hotelVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new R0807().hotelStart();
	}

}

class HotelVO implements Serializable{
	String roomNo;
	String name;
	
	public HotelVO(String roomNo, String name) {
		this.roomNo = roomNo;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
}
