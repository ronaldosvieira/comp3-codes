package excecoes;

public class DatabaseAccessException extends Exception {
	private String message;
	private static final long serialVersionUID = -3618476050367111142L;

	public DatabaseAccessException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
