package roteiros.comodo;

import java.util.List;

import entidades.Comodo;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class LerComodoTS {

	public static List<Comodo> execute() throws DatabaseAccessException {
		try (ComodoBanco bd = new ComodoBanco()) {
			return bd.get();
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
