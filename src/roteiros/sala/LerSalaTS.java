package roteiros.sala;

import java.util.List;

import entidades.Comodo;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class LerSalaTS {

	public static List<Comodo> execute() throws DatabaseAccessException {
		List<Comodo> salas = null;
		
		try (ComodoBanco bd = new ComodoBanco()) {
			salas = bd.get("sala");
			
			return salas;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
