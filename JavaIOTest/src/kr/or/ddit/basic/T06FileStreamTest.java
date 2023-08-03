package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// 파일출력 예제
public class T06FileStreamTest {

	public static void main(String[] args) {
		
		// 파일에 내용물 저장
		FileOutputStream fos = null;
		
		try {
			
			//File file = new File("d:/D_Other/out.txt");
			//fis = new FileOutputStream(file);
			fos = new FileOutputStream("d:/D_Other/out.txt");   //string으로 경로 지정해도 됨
			
			for(char ch='a'; ch <= 'z'; ch++) {
				fos.write(ch);
			}
			
			System.out.println("파일에 쓰기 작업 완료!!!");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// 손쉽게 읽어오기......
		FileInputStream fis = null;
		
		try {
			
			fis = new FileInputStream("d:/D_Other/out.txt");
			
			int data = 0;
			
			while((data = fis.read()) != -1) {
				System.out.print((char)data);
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
