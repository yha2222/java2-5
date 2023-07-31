package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02FileTest {
	public static void main(String[] args) {
		
		//파일 객체라 더 유용
		File f1 =new File("d:/D_Other/sample.txt");
		File f2 =new File("d:/D_Other/test.txt");
		
		if(f1.exists()) {
			System.out.println(f1.getAbsolutePath() + "은 존재합니다.");
		}else {
			System.out.println(f1.getAbsolutePath() + "은 존재하지 않습니다.");
			try {
				if(f1.createNewFile()) {
					System.out.println(f1.getAbsolutePath() + "파일을 새로 만들었습니다.");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		if(f2.exists()) {
			System.out.println(f2.getAbsolutePath() + "은 존재합니다.");
		}else {
			System.out.println(f2.getAbsolutePath() + "은 존재하지 않습니다.");
		}
		System.out.println("---------------------------------------------");
		
		// 디렉토리인 경우, 파일 가져오기 listFiles => 파일만! 필요할 때
		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles();
		for(File f : files) {
			System.out.print(f.getName() + " => ");
			if(f.isFile()) {
				System.out.println("파일");
			}else if(f.isDirectory()) {
				System.out.println("디렉토리(폴더)");
			}
		}
		System.out.println("============================================");
		String[] strFiles = f3.list();
		for(String str : strFiles) {
			System.out.println(str);
		}
		System.out.println("--------------------------------------------");
	
		// 출력할 디렉토리 정보를 갖는 파일 객체
		File f4 = new File("d:/D_Other");
		
		displayFileList(f4);
	
	}
	/*
	 	지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주는 메서드
	 	@param f4 파일과 디렉토리 목록을 보고 싶은 디렉토리(폴더)
	 */
	// 메인(static)에서 만드니까 자동 static
	// 인스턴스로 하려면 객체만들어서 호출 new T02FileTest.displayFileList(f4);
	private static void displayFileList(File dir) {
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리 내용");
		
		// 디렉토리 안의 모든 파일 목록을 가져옴
		File[] files = dir.listFiles();
		
		// 하위 디렉토리 정보를 저장할 Arraylist 생성(File 객체 인텍스값 저장용)
		List<Integer> subDirList = new ArrayList<Integer>();
		
		//날짜를 출력하기 위한 형식 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		for(int i=0; i<files.length; i++) {
			String attr = ""; // 파일 속성 정보(읽기, 쓰기, 숨김, 디렉토리 구분)
			String size = ""; // 파일 크기
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
				subDirList.add(i);  // 인덱스 정보 저장
			}else {
				size = files[i].length() + ""; // long =(+ "")=> String
				attr = files[i].canRead() ? "R" : " ";
				attr += files[i].canWrite() ? "W" : " ";
				attr += files[i].isHidden() ? "H" : " ";
			}
			
			System.out.printf("%s %5s %12s %s\n",  // 기본 우측 정렬, 좌축 정렬(%-5s)
					sdf.format(new Date(files[i].lastModified())),
					attr, size, files[i].getName());
		}
		
		int dirCount = subDirList.size();	// 폴더 개수
		int fileCount = files.length - dirCount;	//파일 개수
		
		System.out.println(fileCount + "개의 파일, "
					+ dirCount + "개의 디렉토리(폴더)");
		System.out.println();
		
		for(Integer i : subDirList) {
			displayFileList(files[i]);
			// files 안에 있는 디렉토리 내용 출력 - 나 자신의 메서드 또 다시 호출 => 재귀호출
		}
	}
}
