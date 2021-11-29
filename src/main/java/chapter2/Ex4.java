package chapter2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ex4 {

	public static void main(String[] args) {
		LocalDateTime ldt = LocalDateTime.now();
		
		String[] patterns = {
			"yyyy-MM-dd HH:mm:ss",
			"yy�� MM�� dd�� E����",
			"yyyy-MM-dd hh:mm:ss a",
			"������ ������ D��° ���Դϴ�.",
			"������ �� ���� d��° ���Դϴ�.",
			"������ �� ���� w��° ���Դϴ�.",
			"������ �� ���� W��° ���Դϴ�.",
			"������ �� ���� W��° E���� �Դϴ�."
		};
		
		for(String pattern : patterns) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
			
			System.out.println(dtf.format(ldt));
		}
	}

}
