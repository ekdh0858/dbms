package chapter2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ex3 {

	public static void main(String[] args) {
		//���� ��¥�� �ð� ������ ���� �ִ� LocalDateTime ��ü ����
		LocalDateTime localDateTime = LocalDateTime.now();
		
		System.out.println(localDateTime);
		//���� ������ ��¥�ͽð� ������ ���� �ִ� LocaldateTime ��ü ����
		LocalDateTime ldt =LocalDateTime.of(2021, 11, 25, 10, 43, 32);
		
		System.out.println(ldt);
		
		// LocalDateTime ��ü�� ���� �ִ� ��¥ ������ ����
		LocalDate localDate = ldt.toLocalDate();
		// LocalDateTime ��ü�� ���� �ִ� �ð� ������ ����
		LocalTime localTime = ldt.toLocalTime();
		
		System.out.println("localDate = "+localDate);
		System.out.println("localTime = "+localTime);
	}

}
