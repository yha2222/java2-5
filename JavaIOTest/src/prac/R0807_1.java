package prac;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class R0807_1 {

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

	public R0807_1() {
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
			
			Set<String> keyset = hotelBook.keySet();
			for(String key : keyset) {
				oos.writeObject(hotelBook.get(key));
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			HotelVO hv = null;
			
			while((obj = ois.readObject()) != null) {
				hv = (HotelVO)obj;
				hotelBook.put(hv.getRoomNo(), hv);
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		new R0807_1().hotelStart();
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
