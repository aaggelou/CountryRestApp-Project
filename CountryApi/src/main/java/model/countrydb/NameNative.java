package model.countrydb;

public class NameNative {
	//fields
	private String official;
    private String common;
    
	/*public NameNative(String official, String common) {
		super();
		this.official = official;
		this.common = common;
	}*/
    
    //getters and setters
	public String getOfficial() {
		return official;
	}
	public void setOfficial(String official) {
		this.official = official;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
    
	@Override
    public String toString() {
        return "NameNative{" +
                "official='" + official + '\'' +
                ", common='" + common + '\'' +
                '}';
    }
}
