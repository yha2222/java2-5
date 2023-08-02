package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// 입출력 성능향상을 위한 보조스트림 예제2 (문자 기반의 Buffered 스트림 예제)
public class T13BufferedIOTest {
	public static void main(String[] args) {
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			
			fr = new FileReader(
					// 현재 프로젝트 기준 상대경로
					"./src/kr/or/ddit/basic/T12BufferedIOTest.java");
			
			br = new BufferedReader(fr);
			
			String tmpStr = "";
			
			while((tmpStr = br.readLine()) != null) { // 읽을 데이터 있으면
				System.out.println(tmpStr);
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
