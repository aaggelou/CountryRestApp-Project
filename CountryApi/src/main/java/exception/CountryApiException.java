package exception;

public class CountryApiException  extends Exception {

	public CountryApiException() {
        super();
    }
	
	public CountryApiException(String message) {
        super(message);
    }

    public CountryApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryApiException(Throwable cause) {
        super(cause);
    }

    protected CountryApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
