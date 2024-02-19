package model;

import java.util.List;
import java.util.Map;

import model.countrydb.Currency;
import model.countrydb.Name;

public class CountryResponse {
	//fields
	private Name name;
	private List<String> capital;
    private Map<String, Currency> currencies;
    private String population;
    private List<String> continents;
    private Map<String, String> languages;
    
    
	/*public CountryResponse(Name name, List<String> capital, Map<String, Currency> currencies, String population,
			List<String> continents, Map<String, String> languages) {
		super();
		this.name = name;
		this.capital = capital;
		this.currencies = currencies;
		this.population = population;
		this.continents = continents;
		this.languages = languages;
	}*/

//getters and setters
	public Name getName() {
		return name;
	}


	public void setName(Name name) {
		this.name = name;
	}


	public List<String> getCapital() {
		return capital;
	}


	public void setCapital(List<String> capital) {
		this.capital = capital;
	}


	public Map<String, Currency> getCurrencies() {
		return currencies;
	}


	public void setCurrencies(Map<String, Currency> currencies) {
		this.currencies = currencies;
	}


	public String getPopulation() {
		return population;
	}


	public void setPopulation(String population) {
		this.population = population;
	}


	public List<String> getContinents() {
		return continents;
	}


	public void setContinents(List<String> continents) {
		this.continents = continents;
	}


	public Map<String, String> getLanguages() {
		return languages;
	}


	public void setLanguages(Map<String, String> languages) {
		this.languages = languages;
	}
    //toString
	@Override
    public String toString() {
        return "Country{" +
                "name=" + name +
                ", currencies=" + currencies +
                ", capital=" + capital +
                ", languages=" + languages +
                ", population=" + population +
                ", continents=" + continents +
                '}';
    }
    
}
