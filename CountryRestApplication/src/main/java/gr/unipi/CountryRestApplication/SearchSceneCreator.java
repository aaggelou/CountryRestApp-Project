package gr.unipi.CountryRestApplication;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import exception.CountryApiException;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
//import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CountryResponse;
import model.countrydb.Currency;
import services.CountryApi;
import services.CountryApiService;


public class SearchSceneCreator extends SceneCreator implements EventHandler<MouseEvent>{
	
	GridPane rootGridPane;
	Button backBtn, searchBtn;
	TableView<CountryInformation> countryTableView;
	ArrayList<CountryInformation> countryList;
	ChoiceBox choiceBox;
	TextField searchField;
	
	
	public SearchSceneCreator(double width, double height) {
		
		super(width, height);
		// initialize things
		countryList = new ArrayList<CountryInformation>();
		rootGridPane = new GridPane();
		backBtn = new Button("Go Back");
		searchBtn = new Button("Search");
		countryTableView = new TableView<CountryInformation>();
		choiceBox = new ChoiceBox();
		searchField = new TextField();
		
		choiceBox.getItems().add("Search by Name");
		choiceBox.getItems().add("Search by Language");
		choiceBox.getItems().add("Search by Currency");
		
		
		
		 //Customize Root Grid Pane
		rootGridPane.setVgap(10);
		rootGridPane.setHgap(10);
		rootGridPane.add(countryTableView, 0, 0);
		rootGridPane.add(backBtn, 1, 3);
		rootGridPane.add(searchBtn, 1, 2);
		rootGridPane.add(choiceBox, 1, 0);
		rootGridPane.add(searchField, 1, 1);
		
	
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
		
		//countryTableView.getItems().addAll(countryList);
		countryTableView.setPrefWidth(900); // Set preferred width to 400 pixels
		countryTableView.setPrefHeight(300);
		
		
		
		// Attach events to buttons
		
		backBtn.setOnMouseClicked(this);
		searchBtn.setOnMouseClicked(this);
		
		
		
		
		
	}
	
	
	

	@Override
	Scene createScene() {
		return new Scene(rootGridPane, width, height);
	}
	
	public void handle(MouseEvent event) {
		if (event.getSource() == backBtn) {
			App.primaryStage.setTitle("CountryRestApp");
			App.primaryStage.setScene(App.mainScene);
		}
		else if(event.getSource() == searchBtn) {
			//Περιορισμοί για να μην μπορεί να θέσει ο χρήστης μόνο κενά ή ειδικούς χαρακτήρες
			String text = searchField.getText();
			String spaceRegex = "^\\s*$";
			 String specialCharRegex = "[^a-zA-Z0-9\\s]";
			 Pattern spacePattern = Pattern.compile(spaceRegex);
		     Pattern specialCharPattern = Pattern.compile(specialCharRegex);
		     Matcher spaceMatcher1 = spacePattern.matcher(text);
		     Matcher specialCharMatcher1 = specialCharPattern.matcher(text);
			if(spaceMatcher1.matches() || specialCharMatcher1.find()) {
				Alert alert = new Alert(Alert.AlertType.WARNING);//Εμφάιση Alert
				alert.setTitle("Information For User");
				 alert.setHeaderText(null);
				 alert.setContentText("You can't insert only spaces or special characters!");
				// alert.getButtonTypes().add(ButtonType.OK);
				 alert.showAndWait();
			}
			else {
				String valueCchoiceBox = (String) choiceBox.getValue();
				if(valueCchoiceBox==null) {//Πρέπει να επιλέξει κάτιο χρήστρης από το ChoiceBox
					Alert alert = new Alert(Alert.AlertType.WARNING);//Εμφάιση Alert
					alert.setTitle("Information For User");
					 alert.setHeaderText(null);
					 alert.setContentText("You have to choose one option from the ChoiceBox");
					// alert.getButtonTypes().add(ButtonType.OK);
					 alert.showAndWait();
				}
				else if((valueCchoiceBox.equals("Search by Name"))) {
					final CountryApiService countryInfoByName = CountryApi.getCountryDBService();
					try {
						final List<CountryResponse> results = countryInfoByName.getCountryInfo("name", text);//Κλήση Country Api--->Αναζήτηση με όνομα
						ActionHistory.recordAction("name"+","+text); // Προσθήκη ενέργειας στο ιστορικό ενεργειών
						
						

						if(results.size()==0) {
							countryTableView.setPlaceholder(
								    new Label("No rows to display"));
						}
						else {
							countryList.clear();
							
							for(int i=0; i<results.size(); i++) {
							
								
								CountryInformation country =new CountryInformation(results.get(i));//Δημιουργια Αντικειμένων CountryInformation
								countryList.add(country);
							}
							tableSync();
							
							
						}
						
					} catch (CountryApiException e) {
						countryTableView.getItems().clear();
						countryTableView.setPlaceholder(
							    new Label(e.getMessage()));
						e.printStackTrace();
					}
					
					
					
					clearTextFields();
				}
				
				else if(valueCchoiceBox.equals("Search by Currency")) {
					final CountryApiService countryInfoByName = CountryApi.getCountryDBService();
					
					try {
						final List<CountryResponse> results = countryInfoByName.getCountryInfo("currency", text); //Κλήση Country Api--->Αναζήτηση με νόμισμα
						ActionHistory.recordAction("currency"+","+text);// Προσθήκη ενέργειας στο ιστορικό ενεργειών
						
						if(results.size()==0) {
							countryTableView.setPlaceholder(
								    new Label("No rows to display"));
						}
						else {
							countryList.clear();
							
							for(int i=0; i<results.size(); i++) {
							
								
								CountryInformation country =new CountryInformation(results.get(i));//Δημιουργια Αντικειμένων CountryInformation
								countryList.add(country);
							}
							tableSync();
							
							
						}
						
						
						
					} catch (CountryApiException e) {
						countryTableView.getItems().clear();
						countryTableView.setPlaceholder(
							    new Label(e.getMessage()));
						e.printStackTrace();
					}
				}
				
				else if(valueCchoiceBox.equals("Search by Language")) {
					final CountryApiService countryInfoByName = CountryApi.getCountryDBService();
					try {
						final List<CountryResponse> results = countryInfoByName.getCountryInfo("lang", text);//Κλήση Country Api--->Αναζήτηση με γλώσσα
						ActionHistory.recordAction("lang"+","+text);// Προσθήκη ενέργειας στο ιστορικό ενεργειών
						
						if(results.size()==0) {
							countryTableView.setPlaceholder(
								    new Label("No rows to display"));
						}
						else {
							countryList.clear();
							
							for(int i=0; i<results.size(); i++) {
							
								
								CountryInformation country =new CountryInformation(results.get(i));//Δημιουργια Αντικειμένων CountryInformation
								countryList.add(country);
							}
							tableSync();
							
							
						}
						
						
					} catch (CountryApiException e) {//catch errors
						countryTableView.getItems().clear();
						countryTableView.setPlaceholder(
							    new Label(e.getMessage()));
						e.printStackTrace();
					}
					
				}
				
			}
		}
		
		
		
	}
	
	
	public void tableSync() {
		List<CountryInformation> items = countryTableView.getItems();
		items.clear();

		for (CountryInformation country : countryList) {
			if (country instanceof CountryInformation) {
				items.add(country);
			}
		}

	}
	
	public void clearTextFields() {
		searchField.setText("");
		
	}
	
	
	
	
}
