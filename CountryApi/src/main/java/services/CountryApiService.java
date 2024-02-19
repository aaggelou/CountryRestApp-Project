package services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.CountryApiException;
//import model.CountryInformation;
import model.CountryResponse;
import model.countrydb.ErrorResponse;

public class CountryApiService {
	private final String API_URL;

	
	public CountryApiService(String aPI_URL) {
		API_URL = aPI_URL;
	
	}
	//μέθοδος για να φέρει όλες τις χώρες
	public List<CountryResponse> getAllCountries() throws CountryApiException {
		List<CountryResponse> result = getAPIData("all", null, API_URL);
		
		return result;
	}
	
	
	public List<CountryResponse> getCountryInfo(String apiService, String parameter) throws CountryApiException {
		if (apiService.equals("name")) {  //μέθοδος--> αναζήτηση με όνομα---> επιστρέφει λίστα με CountryResponse
			List<CountryResponse> result = getAPIData("name", parameter,  API_URL);
			return result;
		}
		else if(apiService.equals("lang")) {//μέθοδος--> αναζήτηση με γλώσσα---> επιστρέφει λίστα με CountryResponse
			List<CountryResponse> result = getAPIData("lang", parameter, API_URL);
			return result;
		}
		else if(apiService.equals("currency")) {
			System.out.println("Inserted currency"); //μέθοδος--> αναζήτηση με νόμισμα---> επιστρέφει λίστα με CountryResponse
			List<CountryResponse> result = getAPIData("currency", parameter, API_URL);
			return result;
		}
		
		else {
			
			throw new CountryApiException("Error occurred on API call: Please choose the correct Api Service");
		}
		
	
		
		
	}
	
	
	
	/*public List<CountryResponse> getCountryInfoByName(String parameter) throws CountryApiException {
		List<CountryResponse> result = getAPIData("name", parameter, API_URL);
		
		return result;
	}
	
	public List<CountryResponse> getCountryInfoByLanguage(String parameter) throws CountryApiException {
		List<CountryResponse> result = getAPIData("lang", parameter, API_URL);
		
		return result;
	}
	
	public List<CountryResponse> getCountryInfoByCurrency(String parameter) throws CountryApiException {
		List<CountryResponse> result = getAPIData("currency", parameter, API_URL);
		
		return result;
	}*/
	
	
	
	
	private List<CountryResponse> getAPIData(String apiFunction, String parameter, String API_URL)
			throws CountryApiException {
		try {
			
			final URIBuilder uriBuilder = new URIBuilder(API_URL);
					
					
					if (parameter != null && !parameter.trim().isEmpty()) {//απόφαση σχετικά με το πως θα φτιάξει το url.
						uriBuilder.setPathSegments("v3.1", apiFunction, parameter);
						uriBuilder.addParameter("fields", "name,capital,currencies,languages,continents,population");
					}
					else {
						uriBuilder.setPathSegments("v3.1", apiFunction);
						uriBuilder.addParameter("fields", "name,capital,currencies,languages,continents,population");
					}
					
					final URI uri = uriBuilder.build();
					final HttpGet getRequest = new HttpGet(uri);
					final CloseableHttpClient httpclient = HttpClients.createDefault();
					try (CloseableHttpResponse response = httpclient.execute(getRequest)) {
						final HttpEntity entity = response.getEntity();
						final ObjectMapper mapper = new ObjectMapper();
						
						if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
							ErrorResponse errorResponse = mapper.readValue(entity.getContent(), ErrorResponse.class);
							if (errorResponse.getMessage() != null)
								throw new CountryApiException("Error occurred on API call: " + errorResponse.getStatus()+ " "+ errorResponse.getMessage()+ " " + errorResponse.getAdditionalProperties());
						}
						//deserialize data---> αποθηκεύει τα δεδομένα σε μία λίστα που έχει αντικείμενα CountryResponse
						List<CountryResponse>  country = mapper.readValue(entity.getContent(), new TypeReference<List<CountryResponse>>() {});
						return country;
						
					} catch (IOException e) {
						throw new CountryApiException("Error requesting data from the The Country API.", e);
					}
						
			
			
			
		} catch (URISyntaxException e) {
			throw new CountryApiException("Unable to create request URI.", e);
		}
	}
	
	

}
	
	
	
	

