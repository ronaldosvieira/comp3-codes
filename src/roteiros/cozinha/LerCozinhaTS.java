package roteiros.cozinha;

import java.util.List;

import entidades.Comodo;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class LerCozinhaTS {

	public static List<Comodo> execute() throws DatabaseAccessException {
		try (ComodoBanco bd = new ComodoBanco()) {
			List<Comodo> cozinhas = bd.get("cozinha");
			
			return cozinhas;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
