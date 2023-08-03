package prac;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class P0804 {
	public static void main(String[] args)  {
		
		FileOutputStream fos = null;
		
		try {
			
			fos = new FileOutputStream("c:/Prac/out.txt");
			
			for(char ch='a'; ch<='z'; ch++) {
				fos.write(ch);
			}
			
			System.out.println("파일에 쓰기 작업 완료");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("c:/Prac/out.txt");
			
			int data = 0;
			
			while((data = fis.read()) != -1) {
				System.out.println((char)data);
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
