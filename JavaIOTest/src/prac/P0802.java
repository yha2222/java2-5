package prac;

import java.io.File;
import java.io.IOException;

public class P0802 {
	public static void main(String[] args) throws IOException {
		
		File file1 = new File("d:/Prac/test.txt");
		System.out.println("파일명: " + file1.getName());
		System.out.println("파일 여부: " + file1.isFile());
		System.out.println("디렉토리 여부: " + file1.isDirectory());
		System.out.println();
		
		File file2 = new File("d:/Prac");
		System.out.println(file2.getName() + "은");
		if(file2.isFile()) {
			System.out.println("파일입니다.");
		}else if(file2.isDirectory()) {
			System.out.println("폴더입니다.");
		}
		System.out.println();
		
		File file3 = new File(file2, "test.txt");
		System.out.println(file3.getName() + "의 용량은 " + file3.length() + "입니다.");
		System.out.println();
		
		File file4 = new File("./Prac/test//..", "test.txt");
		System.out.println("경로: " + file4.getPath());
		System.out.println("절대경로: " + file4.getAbsolutePath());
		System.out.println("상대경로: " + file4.getCanonicalPath());
		System.out.println();
		
		File file5 = new File("d:/Prac/연습용");
		if(file5.mkdir()) {
			System.out.println(file5.getName() + "만들기 성공");
		}else {
			System.out.println(file5.getName() + "만들기 실패");
		}
		
		File file6 = new File("d:/Prac/src");
		if(file5.mkdirs()) {
			System.out.println(file6.getName() + "만들기 성공");
		}else {
			System.out.println(file6.getName() + "만들기 실패");
		}
	}
}
