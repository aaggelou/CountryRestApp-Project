package gr.unipi.CountryRestApplication;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {
	
	// Stage
		static Stage primaryStage;
		// Scenes
		static Scene mainScene, countryScene, searchScene, historyScene;
	
	
    @Override
    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage;//Δημιουργία Stage
		//Αρχικοποίηση των mainScene, countryScene, searchScene, historyScene που είναι οι σκηνές
		SceneCreator mainSceneCreator = new MainSceneCreator(680, 300);
		mainScene = mainSceneCreator.createScene();
		SceneCreator countrySceneCreator = new CountrySceneCreator(1000, 400);
		countryScene=countrySceneCreator.createScene();
		SceneCreator searchSceneCreator = new SearchSceneCreator(1100, 500);
		searchScene=searchSceneCreator.createScene();
		SceneCreator historySceneCreator = new HistorySceneCreator(1100, 500);
		historyScene=historySceneCreator.createScene();
		
		//Βάζουμε τίτλο στο primaryStage
		primaryStage.setTitle("CountryRestApp");
		//Δηλώνουμε ποια σκηνή θα δούμε
		primaryStage.setScene(mainScene);
		//Τα εμφανίζουμε
		primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
    
}