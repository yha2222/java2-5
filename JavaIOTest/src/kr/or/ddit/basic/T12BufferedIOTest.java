package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// 성능향상을 위한 보조스트림 예제 (바이트 기반[output]의 Buffered 스트림 사용 예제) 
public class T12BufferedIOTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		BufferedOutputStream bis = null;
		
		try {
			
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			// 버퍼 크기를 지정하지 않으면 기본적으로 8192byte(8KB)로 설정됨
			bos = new BufferedOutputStream(fos, 5);  // 버퍼 많이 잡을수록 용량..
								  // 내부 버퍼 이용, 5개씩 모았다가 처리
			
			for(char ch = '1'; ch <= '9'; ch++) {
				bos.write(ch);  
			}
			
			System.out.println("작업 끝");
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				bos.close(); // 보조 스트림만 닫아도 됨
			} catch (IOException e) {
				e.printStackTrace();
			} 	
		}
	}
}
