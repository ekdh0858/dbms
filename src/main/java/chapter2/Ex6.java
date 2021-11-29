package chapter2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ex6 {

	public static void main(String[] args) {
		String dateTime = "2021년 08월 11일 13시 59분 02초";
//		String dateTime = "2021-03-26T00:55:28";
//		dateTime = dateTime.replace("년 ","-");
//		dateTime = dateTime.replace("월 ","-");
//		dateTime = dateTime.replace("일 ","T");
//		dateTime = dateTime.replace("시 ",":");
//		dateTime = dateTime.replace("분 ",":");
//		dateTime = dateTime.replace("초","");
		
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		
		LocalDateTime ldt = LocalDateTime.parse(dateTime,dft);
		
		System.out.println("ldt = "+ldt);
		
	}

}
