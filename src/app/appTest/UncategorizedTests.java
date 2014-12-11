package formjava.module2.travelAgency;

import org.junit.Test;

import formjava.module2.travelAgency.model.Hotel;
import formjava.module2.travelAgency.model.Pack;
import formjava.module2.travelAgency.model.Program;
import formjava.module2.travelAgency.model.SimpleProduct;

public class UncategorizedTests {

	@Test
	public void hotelTest() {
		Hotel h = new Hotel("Hotel Glorioso", "Description", 1904.00, Hotel.Stars.SEVEN);
		Hotel anotherh = new Hotel("The one who shall not be named", "Description", 1906.00, Hotel.Stars.ONE);
	}
	
	@Test
	public void hotelPackTest() {
		Pack<Hotel> hp = new Pack<>("SLB Hotels");
		
		Hotel h = new Hotel("Hotel Glorioso", "Description", 1904.00, Hotel.Stars.SEVEN);
		Hotel anotherh = new Hotel("The one who shall not be named", "Description", 1906.00, Hotel.Stars.ONE);
		
		
		hp.add(h);
		
		SimpleProduct sp = new SimpleProduct("Uma cena qq", "", 10.0);
		
		//hp.add(sp);
		
		Program hp1 = new Program("More SLB Hotels");
		hp1.add(h);
		hp1.add(hp1);
		
		Program sp1;
		
		//hp1.add(sp1); - Compilation error
		
		Program pp = null;
		
		Pack<Hotel> sp2 = new Pack<Hotel>("Hotel pack");
		
		
		
		// sp2.add(pp); - Compilation error
		
		pp.add(sp2);
		
		//hp1.add(sp);
		//Program<Product> pp;
		
	}
	

}
