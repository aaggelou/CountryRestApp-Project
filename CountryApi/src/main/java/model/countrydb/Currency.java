package model.countrydb;

public class Currency {
	//fields
	private String name;
    private String symbol;
    
    
	/*public Currency(String name, String symbol) {
		super();
		this.name = name;
		this.symbol = symbol;
	}*/

//getters and setters
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	 //toString
	@Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
