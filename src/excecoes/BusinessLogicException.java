package excecoes;

public class BusinessLogicException extends Exception {
	private String message;
	private static final long serialVersionUID = -2445375960724543419L;

	public BusinessLogicException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
