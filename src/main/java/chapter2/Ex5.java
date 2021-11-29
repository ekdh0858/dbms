package chapter2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ex5 {

	public static void main(String[] args) {
		// datetime 문자열이 갖고 있는 날짜와 시간을 사용해서 
		// LocalDateTime 객체를 만드세요.
		String dateTime = "2021-03-26T00:55:28";
		
		String[] info = dateTime.split("T");
		String date = info[0];
		String time = info[1];
		
		
		LocalDateTime localdatetime = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
//		String date = "2021-03-26";
//		String time = "00:55:28";
//		
//		LocalDate ld = LocalDate.parse(date);
//		LocalTime lt = LocalTime.parse(time);
//		
//		System.out.println(ld);
//		System.out.println(lt);
//		
//		LocalDateTime ldt = LocalDateTime.of(ld, lt);
//		
//		System.out.println(ldt);
	}

}
