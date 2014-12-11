package formjava.module2.travelAgency;

import java.util.function.Predicate;

import org.junit.Test;

import formjava.module2.travelAgency.model.Hotel;
import formjava.module2.travelAgency.model.Product;
import formjava.module2.travelAgency.model.ProductFilteringCriteria;
import formjava.module2.travelAgency.model.repos.InMemoryProductRepository;

public class InMemoryRepositoryTests {

	/*
	private class IsHotelCriteria implements Predicate<Product> {
		@Override
		public boolean test(Product product) 
		{
			return product instanceof Hotel;
		}
		
	}
	*/
	
	@Test
	public void test() 
	{
		InMemoryProductRepository repository = new InMemoryProductRepository();
		
		// ...
		
		repository.getAll();
		repository.getAll(new ProductFilteringCriteria.IsBelowPrice(500));
		//repository.getProducts(new IsHotelCriteria());
		
		repository.getAll(new Predicate<Product>() {
			@Override
			public boolean test(Product product) 
			{
				return product instanceof Hotel;
			}
		});
		
		repository.getAll(new Predicate<Product>() {
			@Override
			public boolean test(Product product) 
			{
				final double thresholdPrice = 250;
				return product instanceof Hotel && product.getPrice() < thresholdPrice;
			}
		});

	}

}
