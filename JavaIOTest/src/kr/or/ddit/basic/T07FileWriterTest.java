package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class T07FileWriterTest {
	public static void main(String[] args) {
		
		//사용자가 입력한 내용을 그대로 파일에 저장
		
		// 콘솔(표준 입력장치)과 연결된 입력용 문자 스트림 생성
		// InputStreamReader => 바이트기반 스트림(기본 스트림)을 문자 기반 스트림(단독 불가)으로 변환해주는 보조스트림
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = null; // 파일 출력용 문자 기반 스트림
		
		try {
			
			fw = new FileWriter("d:/D_Other/testChar.txt");
			
			int data = 0;
			
			System.out.println("아무거나 입력하세요...(입력 후 Ctrl+z 눌러주세요.");
			
			// 콘솔에서 입력할 때 입력 끝 표시는 Ctrl + z 키를 누르면 됨
			while((data = isr.read()) != -1) {
				//콘솔에서 입력받은 값을 파일에 저장하기 
				fw.write(data);  // write => 한글 저장 필요 => 문자기반 스트림 => 한 char씩 (byte씩 말고)
			}
			
			System.out.println("작업 끝...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fw.close();
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
