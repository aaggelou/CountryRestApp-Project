package services;

public class CountryApi {
	public static CountryApiService getCountryDBService() {
		// API key needed. Register and generate API KEY ----------> Πααρέχει το URL για να γίνει κλήση στο API
		return new CountryApiService("https://restcountries.com/");
	}
}
