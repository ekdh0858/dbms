package chapter2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ex2 {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		
		SimpleDateFormat sdf;
		
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = sdf.format(date);
		
		System.out.println("1. "+formatted);
		
		sdf=new SimpleDateFormat("yyyy�� MM�� dd��");
		formatted=sdf.format(date);
		System.out.println("2. "+formatted);
		
		sdf=new SimpleDateFormat("yy�� M�� d��");
		formatted=sdf.format(date);
		System.out.println("3. "+formatted);
		
		sdf=new SimpleDateFormat("yy�� M�� d�� HH:mm:ss");
		formatted=sdf.format(date);
		System.out.println("4. "+formatted);
		
		sdf=new SimpleDateFormat("yy�� M�� d�� H:m:s");
		formatted=sdf.format(date);
		System.out.println("5. "+formatted);
		
		sdf=new SimpleDateFormat("yy�� M�� d�� HH:mm:ss.SSS");
		formatted=sdf.format(date);
		System.out.println("6. "+formatted);
		
		sdf=new SimpleDateFormat("yy�� M�� d�� HH:mm:ss a");
		formatted=sdf.format(date);
		System.out.println("7. "+formatted);
		
		sdf=new SimpleDateFormat("yy�� M�� d�� h:m:s");
		formatted=sdf.format(date);
		System.out.println("8. "+formatted);
	}

}
