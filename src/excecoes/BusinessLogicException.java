package excecoes;

public class BusinessLogicException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;

	public BusinessLogicException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
