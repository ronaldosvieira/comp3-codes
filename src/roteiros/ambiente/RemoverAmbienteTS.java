package roteiros.ambiente;

import excecoes.DatabaseAccessException;
import persistencia.AmbienteBanco;

public class RemoverAmbienteTS {

	public static void execute(int id) throws DatabaseAccessException {
		try (AmbienteBanco bd = new AmbienteBanco()) {
			bd.remove(id);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
