package app.elements;

import utils.Consultant;

public class teste {

	public teste() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		User u1 = new User("name1", "pass1", "email1", "full1");
		Consultant u2 = new Consultant("cons", 2.0, 4.0, 1);
		
		System.out.println(u1.getEmail());
		String a = u1.getEmail();
		a="ssss";
		System.out.println(a+u1.getEmail());
		
		System.out.println(u2.getCID());
		System.out.println(u2.getCostPerHour());
		double b = u2.getCostPerHour();
		long c = u2.getCID();
		b = 3.0;
		c = 3;
		System.out.println(b+" " + c + " " + u2.getCostPerHour() + " " + u2.getCID());
		
		System.out.println(u1.equals(null));
	}

}
