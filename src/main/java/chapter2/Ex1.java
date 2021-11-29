package chapter2;

import java.text.DecimalFormat;

public class Ex1 {

	public static void main(String[] args) {
		int balance = 123456789; //������ �ܾ�
		
		System.out.println("balance = "+ balance);
				
		// ������ ������ �˾ƺ��� ���� �� �ڸ����� �޸��� ���� ���� �������� ��ȯ
		DecimalFormat df = new DecimalFormat("#,###");
		String formatted = df.format(balance);
		
		System.out.println("balance = "+ formatted);
		
		double avg = 87.53710297;
		// �Ҽ��� �� °�ڸ����� �ݿø��ؼ� �� °�ڸ������� ǥ���ϵ���
		df = new DecimalFormat("#.##");
		formatted = df.format(avg);
		
		System.out.println("avg = "+formatted);
		// ���⼭ formatted�� ���� �ִ� ���ڿ��� �Ǽ��� ���� �ʹٸ�
		// Double.parseDouble �޼��带 Ȱ��
		Double.parseDouble(formatted);
	}

}
