package gr.unipi.CountryRestApplication;

import java.util.List;
import java.util.Map;

import exception.CountryApiException;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
//import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CountryResponse;
import model.countrydb.Currency;
import services.CountryApi;
import services.CountryApiService;

public class CountrySceneCreator extends SceneCreator implements EventHandler<MouseEvent>{
	
	GridPane rootGridPane;
	Button backBtn;
	TableView<CountryInformation> countryTableView;
	ArrayList<CountryInformation> countryList;
	
	
	public CountrySceneCreator(double width, double height) {
		super(width, height);
		// initialize things
		countryList = new ArrayList<CountryInformation>();
		rootGridPane = new GridPane();
		backBtn = new Button("Go Back");
		countryTableView = new TableView<CountryInformation>();
		
		 //Customize Root Grid Pane
			rootGridPane.setVgap(10);
			rootGridPane.setHgap(10);
			rootGridPane.add(countryTableView, 0, 0);
			rootGridPane.add(backBtn, 1, 0);
			
			
			
			final CountryApiService countryInfoAllCountries = CountryApi.getCountryDBService();
			try {
				final List<CountryResponse> results = countryInfoAllCountries.getAllCountries();//Κλήση του CountryApp για να φέρει όλες τις χώρες
				
				if(results.size()==0) {
					countryTableView.setPlaceholder(
						    new Label("No rows to display"));
				}
				else {
					
					
					for(int i=0; i<results.size(); i++) {
						
						
					
						
						CountryInformation country =new CountryInformation(results.get(i));//Δημιουργία Αντικειμένων CountryInformation
						countryList.add(country);//Πέρασμα των Αντικειμένων CountryInformation σε λίστα.
					}
					
				}
			} catch (CountryApiException e) {
				countryTableView.setPlaceholder(
					    new Label("An Error Occurred"));
				e.printStackTrace();
			}
			
			
			// Customize TableView
			//Create Name column
			TableColumn<CountryInformation, String> nameColumn = new TableColumn<>("Name");
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			countryTableView.getColumns().add(nameColumn);
			
			//Create currency column
			TableColumn<CountryInformation, String> currencyColumn = new TableColumn<>("Currency");
			currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));
			countryTableView.getColumns().add(currencyColumn);
			
			//Create capital column
			TableColumn<CountryInformation, String> capitalColumn = new TableColumn<>("Capital");
			capitalColumn.setCellValueFactory(new PropertyValueFactory<>("capital"));
			countryTableView.getColumns().add(capitalColumn);
			
			//Create language column
			TableColumn<CountryInformation, String> languageColumn = new TableColumn<>("Language");
			languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
			countryTableView.getColumns().add(languageColumn);
			
			
			//Create population column
			TableColumn<CountryInformation, String> populationColumn = new TableColumn<>("Population");
			populationColumn.setCellValueFactory(new PropertyValueFactory<>("population"));
			countryTableView.getColumns().add(populationColumn);
			
			
			//Create population continent
			TableColumn<CountryInformation, String> continentColumn = new TableColumn<>("Continent");
			continentColumn.setCellValueFactory(new PropertyValueFactory<>("continent"));
			countryTableView.getColumns().add(continentColumn);
			
			countryTableView.getItems().addAll(countryList);
			countryTableView.setPrefWidth(900); // Set preferred width to 400 pixels
			countryTableView.setPrefHeight(300);
			
			
			// Attach events to buttons
			
			
			backBtn.setOnMouseClicked(this);
			
					
		
	}
	
	@Override
	Scene createScene() {//Υλοποίηση της abstract μεθόδου
		return new Scene(rootGridPane, width, height);
	}
	
	public void handle(MouseEvent event) {//Διαχείριση των ενεργειών όταν γίνει click με το ποντίκι
		if (event.getSource() == backBtn) {
			App.primaryStage.setTitle("CountryRestApp");
			App.primaryStage.setScene(App.mainScene);
		}
		
		
		
	}
	
	
	
}
