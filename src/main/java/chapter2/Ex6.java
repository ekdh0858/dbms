package chapter2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ex6 {

	public static void main(String[] args) {
		String dateTime = "2021�� 08�� 11�� 13�� 59�� 02��";
//		String dateTime = "2021-03-26T00:55:28";
//		dateTime = dateTime.replace("�� ","-");
//		dateTime = dateTime.replace("�� ","-");
//		dateTime = dateTime.replace("�� ","T");
//		dateTime = dateTime.replace("�� ",":");
//		dateTime = dateTime.replace("�� ",":");
//		dateTime = dateTime.replace("��","");
		
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy�� MM�� dd�� HH�� mm�� ss��");
		
		LocalDateTime ldt = LocalDateTime.parse(dateTime,dft);
		
		System.out.println("ldt = "+ldt);
		
	}

}
