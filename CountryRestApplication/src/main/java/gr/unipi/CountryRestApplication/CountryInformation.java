package gr.unipi.CountryRestApplication;

import java.util.List;
import java.util.Map;

import model.CountryResponse;
import model.countrydb.Currency;

public class CountryInformation {
	//fields
	private String name;
	private String currency;
	private String capital;
	private String language;
	private String population;
	private String continent;
	
	/*public CountryInformation(String name, String currency, String capital, String language, String population, String continent) {
		super();
		this.name = name;
		this.currency = currency;
		this.capital = capital;
		this.language = language;
		this.population = population;
		this.continent = continent;
	}*/
//Constructor--->Δέχεται αντικείμενα CountryResponse
	public CountryInformation(CountryResponse theCountryResponse) {
		super();
		this.name = theCountryResponse.getName().getCommon();
		this.currency = getResponseCurrency(theCountryResponse.getCurrencies());
		this.capital = getResponseCapital(theCountryResponse.getCapital());
		this.language = getResponseLanguage(theCountryResponse.getLanguages());
		this.population = theCountryResponse.getPopulation();
		this.continent = getResponseContinet(theCountryResponse.getContinents());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}
	
	
	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}
	//Μέθοδος που διαχειρίζεται την μορφοποίηση των Currency
	public String getResponseCurrency(Map<String, Currency> mapCurrency) {
		String currencyInfo="";
		
		for (Map.Entry<String, Currency> entry : mapCurrency.entrySet()) {
		    String key = entry.getKey();
		    Currency value = entry.getValue();
		    currencyInfo=currencyInfo+value.getName()+"("+value.getSymbol()+")" + ", ";
		    //System.out.println(key + ": " + currency);
		   
		}
		currencyInfo =  currencyInfo.replaceAll(",\\s*$", "");
		 return currencyInfo;
	}
	
	//Μέθοδος που διαχειρίζεται την μορφοποίηση των Capital
	public String getResponseCapital(List<String> capitalInformation) {
		String capitalInfo="";
		
		if(capitalInformation.size()>0) {
			for (int y =0; y<capitalInformation.size(); y++) {
				capitalInfo = capitalInfo+capitalInformation.get(y) + ", ";
				
			}
			}
			else {
				capitalInfo = "Not Available Information";
			}
		capitalInfo =  capitalInfo.replaceAll(",\\s*$", "");
		 return capitalInfo;
	}
	
	
	//Μέθοδος που διαχειρίζεται την μορφοποίηση των Language
	public String getResponseLanguage(Map<String, String> mapLanguage) {
		String languageInfo="";
		
		
		for (Map.Entry<String, String> entry :  mapLanguage.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    languageInfo=languageInfo+value+ ", ";
		    //System.out.println(key + ": " + currency);
		}
		languageInfo =  languageInfo.replaceAll(",\\s*$", "");
		 return languageInfo;
	}
	
	
	//Μέθοδος που διαχειρίζεται την μορφοποίηση των Continent
	public String getResponseContinet(List<String> continetInformation) {
		String continentInfo="";
		
		if(continetInformation.size()>0) {
			for (int y =0; y<continetInformation.size(); y++) {
				continentInfo = continentInfo+continetInformation.get(y) +", ";
				
			}
			}
			else {
				continentInfo = "Not Available Information";
			}
		continentInfo =  continentInfo.replaceAll(",\\s*$", "");
		 return continentInfo;
	}
	
	
}



