package chapter2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ex4 {

	public static void main(String[] args) {
		LocalDateTime ldt = LocalDateTime.now();
		
		String[] patterns = {
			"yyyy-MM-dd HH:mm:ss",
			"yy년 MM월 dd일 E요일",
			"yyyy-MM-dd hh:mm:ss a",
			"오늘은 올해의 D번째 날입니다.",
			"오늘은 이 달의 d번째 날입니다.",
			"오늘은 올 해의 w번째 주입니다.",
			"오늘은 이 달의 W번째 주입니다.",
			"오늘은 이 달의 W번째 E요일 입니다."
		};
		
		for(String pattern : patterns) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
			
			System.out.println(dtf.format(ldt));
		}
	}

}
