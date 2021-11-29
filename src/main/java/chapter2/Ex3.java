package chapter2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ex3 {

	public static void main(String[] args) {
		//현재 날짜와 시간 정보를 갖고 있는 LocalDateTime 객체 생성
		LocalDateTime localDateTime = LocalDateTime.now();
		
		System.out.println(localDateTime);
		//내가 지정한 날짜와시간 정보를 갖고 있는 LocaldateTime 객체 생성
		LocalDateTime ldt =LocalDateTime.of(2021, 11, 25, 10, 43, 32);
		
		System.out.println(ldt);
		
		// LocalDateTime 객체가 갖고 있는 날짜 정보만 추출
		LocalDate localDate = ldt.toLocalDate();
		// LocalDateTime 객체가 갖고 있는 시간 정보만 추출
		LocalTime localTime = ldt.toLocalTime();
		
		System.out.println("localDate = "+localDate);
		System.out.println("localTime = "+localTime);
	}

}
