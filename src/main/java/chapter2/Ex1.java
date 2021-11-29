package chapter2;

import java.text.DecimalFormat;

public class Ex1 {

	public static void main(String[] args) {
		int balance = 123456789; //계좌의 잔액
		
		System.out.println("balance = "+ balance);
				
		// 숫자의 단위를 알아보기 쉽게 세 자리마다 콤마를 찍은 숫자 형식으로 변환
		DecimalFormat df = new DecimalFormat("#,###");
		String formatted = df.format(balance);
		
		System.out.println("balance = "+ formatted);
		
		double avg = 87.53710297;
		// 소숫점 세 째자리에서 반올림해서 두 째자리까지만 표기하도록
		df = new DecimalFormat("#.##");
		formatted = df.format(avg);
		
		System.out.println("avg = "+formatted);
		// 여기서 formatted가 갖고 있는 문자열을 실수로 쓰고 싶다면
		// Double.parseDouble 메서드를 활용
		Double.parseDouble(formatted);
	}

}
