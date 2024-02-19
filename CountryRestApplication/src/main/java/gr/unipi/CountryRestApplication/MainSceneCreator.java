package gr.unipi.CountryRestApplication;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Pos;


public class MainSceneCreator extends SceneCreator implements EventHandler<MouseEvent>{
	// Flow Pane (root node)
			FlowPane rootFlowPane;
			// Main scene buttons
			Button countryButton, searchByParameterBtn, myHistoryBtn;

		public MainSceneCreator(double width, double height) {
			super(width, height);
			rootFlowPane = new FlowPane();
			Tooltip tooltip1 = new Tooltip("See all Countries");
			Tooltip tooltip2 = new Tooltip("See results about Countries for the given Parameters");
			//Tooltip tooltip3 = new Tooltip("See results about Countries for the given Language");
			//Tooltip tooltip4 = new Tooltip("See results about Countries for the given Currency");
			Tooltip tooltip5 = new Tooltip("See the last 5 actions you have made");
			countryButton = new Button("Countries");
			searchByParameterBtn = new Button("Search with Filters");
			//searchByLangBtn = new Button("Search By Language");
			//searchByCurrencyBtn = new Button("Search By Currency");
			myHistoryBtn = new Button("My History");
			
			
			
			
			//attach handle event to countryButton
			countryButton.setOnMouseClicked(this);
			searchByParameterBtn.setOnMouseClicked(this);
			myHistoryBtn.setOnMouseClicked(this);
			
			//setup flow pane
			rootFlowPane.setHgap(10);
			rootFlowPane.setAlignment(Pos.CENTER);
			
			countryButton.setMinSize(120, 30);
			searchByParameterBtn.setMinSize(120, 30);
			//searchByLangBtn.setMinSize(120, 30);
			//searchByCurrencyBtn.setMinSize(120, 30);
			myHistoryBtn.setMinSize(120, 30);
			countryButton.setTooltip(tooltip1);
			searchByParameterBtn.setTooltip(tooltip2);
			//searchByLangBtn.setTooltip(tooltip3);
			//searchByCurrencyBtn.setTooltip(tooltip4);
			myHistoryBtn.setTooltip(tooltip5);
			
			
			//add buttons to rootflowpane
			rootFlowPane.getChildren().add(countryButton);
			rootFlowPane.getChildren().add(searchByParameterBtn);
			//rootFlowPane.getChildren().add(searchByLangBtn);
			//rootFlowPane.getChildren().add(searchByCurrencyBtn);
			rootFlowPane.getChildren().add(myHistoryBtn);
			
			ActionHistory.Initialize();
		}

		@Override
		Scene createScene() {
			return new Scene(rootFlowPane, width, height);
		}
		
		@Override
		public void handle(MouseEvent event) {
			
			
			if (event.getSource() == countryButton) {//Ανάλογα με το κουμπί που θα πατηθεί θα γίνει και η κατάλληλη ενέργεια
				ActionHistory.recordAction("All");//Προσθήκη ενέργειας στο Ιστορικό του χρήστη
				App.primaryStage.setTitle("Country Window");
				App.primaryStage.setScene(App.countryScene);//Πηγαίνουμε στην countryScene
			}

			else if (event.getSource() == searchByParameterBtn) {
				
				App.primaryStage.setTitle("Search Window");
				App.primaryStage.setScene(App.searchScene);//Πηγαίνουμε στην searchScene
			}
		
			
			else if (event.getSource() == myHistoryBtn) {
				App.primaryStage.setTitle("My history Window");
				App.primaryStage.setScene(App.historyScene);//Πηγαίνουμε στην historyScene
			}
		}
	
	
	
}
