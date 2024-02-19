package model.countrydb;

import java.util.Map;

public class Name {
	//fields
	private String common;
    private String official;
    private Map<String, NameNative> nativeName;
    
	/*public Name(String common, String official, Map<String, NameNative> nativeName) {
		super();
		this.common = common;
		this.official = official;
		this.nativeName = nativeName;
	}*/
    
    
  //getters and setters
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	public String getOfficial() {
		return official;
	}
	public void setOfficial(String official) {
		this.official = official;
	}
	public Map<String, NameNative> getNativeName() {
		return nativeName;
	}
	public void setNativeName(Map<String, NameNative> nativeName) {
		this.nativeName = nativeName;
	}
	 //toString
	@Override
    public String toString() {
        return "Name{" +
                "common='" + common + '\'' +
                ", official='" + official + '\'' +
                ", nativeName=" + nativeName +
                '}';
    }
    
}
