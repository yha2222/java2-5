package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

// 파일 읽기 예제
public class T05FileStreamTest {
	public static void main(String[] args) {
		// 파일 읽어들이기
		// byte 기반 스트림 - 문자 기반 아니라 텍스트 파일 안에 한글 읽으면 깨져서 출력
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/test2.txt");
			
			int data = 0;
			
			while((data = fis.read()) != -1) {
				// 읽어 온 데이터 콘솔 출력하기
				System.out.print((char) data);
			}
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fis.close();  // 예외 발생해도 close() 확실히 실행시켜서 깔끔하게 하려고
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
