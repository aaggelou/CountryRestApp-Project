package gr.unipi.CountryRestApplication;

import java.util.ArrayList;
import java.util.List;
//Δημιουργία της συγκεκριμένης κλάσης για να χρησιμοποιήσουμε τις μεθόδους της για καταγραφή του ιστορικού των ενεργειών του χρήστη!!!
public class ActionHistory {

	static private List<String> history;
	static final private int maxSize =5;
	
	public static void recordAction(String action) {
        // Add the action to the history list
        history.add(0, action); // Add to the beginning of the list
        // Keep only the last maxSize actions
        if (history.size() > maxSize) {
            history.subList(maxSize, history.size()).clear();
        }
    }
	//Αρχικοποίηση λίστας
	public static void Initialize() {
		history = new ArrayList<String>();
	}
	//Επιτροφή Λίστας
	 public static List<String> getHistory() {
	        return history;
	    }
	
}
