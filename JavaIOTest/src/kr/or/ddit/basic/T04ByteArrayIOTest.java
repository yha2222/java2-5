package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest {
	
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];		// 자료를 읽을 떄 사용할 배열
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int readBytes = 0;		//읽어 온 바이트 수
		
		// 4바이트까지 한번에 읽을 수 있음 => 한번씩 읽던 거 네 개씩 읽음
		//    =단점=> 4개씩 무조건 끊느라 쓰레기 데이터까지 붙음 ex. temp => [8, 9, 6, 7]
		while((readBytes = bais.read(temp)) != -1) {
			
			System.out.println(" temp => " + Arrays.toString(temp));
			baos.write(temp, 0, readBytes);   // 첫번째 데이터부터 => 읽은 write 개수만큼만
		}
		
		outSrc = baos.toByteArray();
		
		System.out.println(" inSrc => " + Arrays.toString(inSrc));
		System.out.println(" ourSrc => " + Arrays.toString(outSrc));
		//Stream 객체 이용 => byte 단위로 읽어서 차례로 저장
	}
}
