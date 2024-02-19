package gr.unipi.CountryRestApplication;

import javafx.scene.Scene;


import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import exception.CountryApiException;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

//import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CountryResponse;
import model.countrydb.Currency;
import services.CountryApi;
import services.CountryApiService;

public class HistorySceneCreator  extends SceneCreator implements EventHandler<MouseEvent>{

	GridPane rootGridPane;
	Button backBtn, searchBtn, historyBtn;
	TableView<CountryInformation> countryTableView;
	ArrayList<CountryInformation> countryList;
	ChoiceBox<String> choiceBox;
	ObservableList<String> choices;
	
	
	public HistorySceneCreator(double width, double height) {
	
		super(width, height);
		// initialize things
		countryList = new ArrayList<CountryInformation>();
		rootGridPane = new GridPane();
		backBtn = new Button("Go Back");
		searchBtn = new Button("Search");
		historyBtn = new Button("Show History");
		countryTableView = new TableView<CountryInformation>();
		choices = FXCollections.observableArrayList();
		choiceBox = new ChoiceBox<>(choices);
		
		
		
		
		//Customize Root Grid Pane
				rootGridPane.setVgap(10);
				rootGridPane.setHgap(10);
				rootGridPane.add(countryTableView, 0, 0);
				rootGridPane.add(backBtn, 1, 3);
				rootGridPane.add(searchBtn, 1, 2);
				rootGridPane.add(choiceBox, 1, 0);
				rootGridPane.add(historyBtn, 1, 1);
				
				
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
				historyBtn.setOnMouseClicked(this);
				searchBtn.setOnMouseClicked(this);
	}
	
	
	
	
	@Override
	Scene createScene() {//Υλοποίηση της abstract μεθόδου
		return new Scene(rootGridPane, width, height);
	}
	
	
	public void handle(MouseEvent event) {//Διαχείριση των ενεργειών όταν γίνει click με το ποντίκι
		
		if (event.getSource() == backBtn) {
			choices.removeAll(choices); //Άμα πατηθεί το κουμπί για πίσω τότε διαγράφουμε το περιεχόμενο της choices
			historyBtn.setDisable(false); //To historyBtn γίνεται ανενεργό
			App.primaryStage.setTitle("CountryRestApp");
			App.primaryStage.setScene(App.mainScene);
			
		}
		
		
		if (event.getSource() == historyBtn) {
			
			if(ActionHistory.getHistory().size()>0) {//Δημιουργία ελέγχων για την προβολήτου Ιστορικού Ενεργειών
				for(int i =0; i<ActionHistory.getHistory().size(); i++) {
					if(!ActionHistory.getHistory().get(i).equals("All")) {//Πως να φανεί στο ChoiceBox άμα δεν εχει προβάλει όλες τις χώρες
					String inputString=ActionHistory.getHistory().get(i);
					String[] parts = inputString.split(",");
					choices.add("You have searched "+parts[0]+", "+parts[1]);
					}
					else {
						choices.add("You saw All Countries");
					}
				}
			}
			
			
			historyBtn.setDisable(true);
			
		}
		
		else if(event.getSource() == searchBtn) {
			
			String valueCchoiceBox = (String) choiceBox.getValue();
			if(valueCchoiceBox==null) {
				Alert alert = new Alert(Alert.AlertType.WARNING);//Δημιουργία Alert
				alert.setTitle("Information For User");
				 alert.setHeaderText(null);
				 alert.setContentText("You have to choose one option from the ChoiceBox or your history is empty");
				// alert.getButtonTypes().add(ButtonType.OK);
				 alert.showAndWait();
			}
			else if(valueCchoiceBox.equals("You saw All Countries")) {
				final CountryApiService countryInfoAllCountries = CountryApi.getCountryDBService();
				try {
					final List<CountryResponse> results = countryInfoAllCountries.getAllCountries();//Κλήση του CountryApi για να έρθουν όλες οι χώρες
					
					if(results.size()==0) {
						countryTableView.setPlaceholder(
							    new Label("No rows to display"));
					}
					else {
						countryList.clear();
						
						for(int i=0; i<results.size(); i++) {
							
							
						
							
							CountryInformation country =new CountryInformation(results.get(i));//Δημιουργούμε πάλι αντικείμενα CountryInformation για να προβάλουμε τα δεδομένα
							countryList.add(country);
						}
						
						tableSync();
					}
					
				} catch (CountryApiException e) {//γίνεται catch των exceptions
					// TODO Auto-generated catch block
					countryTableView.setPlaceholder(
						    new Label("An Error Occurred"));//Μήνυμα που εμφανίζεται σε περίπτωση που υπάρξει error
					e.printStackTrace();
				}
				
			}
			else {
				
				String originalString =(String) choiceBox.getValue();
				 String phrase = "You have searched ";
				 int index = originalString.indexOf(phrase);
				 
				 if (index != -1) {
			            // Extract the part of the string after the word
			            String extractedString = originalString.substring(index + phrase.length()).trim();
			            
			            // Print the extracted string
			            System.out.println("Extracted string: " + extractedString);
			            
			            String[] parts = extractedString.split(",");
			            
			            System.out.println(parts[0] +","+parts[1]);
			            String nameFunction= parts[0];
			            String valueFunction= parts[1].trim();
			           final CountryApiService countryInfoByName = CountryApi.getCountryDBService();
			           try {
						final List<CountryResponse> results = countryInfoByName.getCountryInfo(nameFunction, valueFunction);//Κλίση του Country Api για να έρθουν τα δεδομένα που αναζητήθηκαν με όνομα, γλώσσα ή νόμισμα
						
						if(results.size()==0) {
							countryTableView.setPlaceholder(
								    new Label("No rows to display"));
						}
						else {
							countryList.clear();
							
							for(int i=0; i<results.size(); i++) {
							
								
								CountryInformation country =new CountryInformation(results.get(i));//Δημιουργούμε πάλι αντικείμενα CountryInformation για να προβάλουμε τα δεδομένα
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
			           
			        } else {
			            System.out.println("Word not found in the string");
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
	
	
	
	
	
	
}
