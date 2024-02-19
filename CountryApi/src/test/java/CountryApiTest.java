import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import exception.CountryApiException;
import model.CountryResponse;
import services.CountryApi;
import services.CountryApiService;

public class CountryApiTest {

	@Test
	public void testCountryInfoByNameAPI() throws CountryApiException {
		final CountryApiService countryInfoByName = CountryApi.getCountryDBService();
		final List<CountryResponse> results = countryInfoByName.getCountryInfo("name", "Greece");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);
	}
	
	@Test
	public void testCountryInfoByLanguageAPI() throws CountryApiException {
		final CountryApiService countryInfoByLanguage = CountryApi.getCountryDBService();
		final List<CountryResponse> results = countryInfoByLanguage.getCountryInfo("lang", "Spanish");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);
	}
	
	@Test
	public void testCountryInfoByCurrenciesAPI() throws CountryApiException {
		final CountryApiService countryInfoByCurrency = CountryApi.getCountryDBService();
		final List<CountryResponse> results = countryInfoByCurrency.getCountryInfo("currency", "cup");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);
	}
	
	
	
	@Test
	public void testCountryInfoAllCountriessAPI() throws CountryApiException {
		final CountryApiService countryInfoAllCountries = CountryApi.getCountryDBService();
		final List<CountryResponse> results = countryInfoAllCountries.getAllCountries();
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);
	}
	/*@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}*/

}
