package roteiros.quarto;

import java.util.List;

import entidades.Comodo;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class LerQuartoTS {

	public static List<Comodo> execute() throws DatabaseAccessException {
		List<Comodo> quartos = null;
		
		try (ComodoBanco bd = new ComodoBanco()) {
			quartos = bd.get("quarto");
			
			return quartos;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
