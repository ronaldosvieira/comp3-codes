package roteiros.comodocomposto;

import java.util.List;

import entidades.Comodo;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class LerComodoCompostoTS {

	public static List<Comodo> execute() throws DatabaseAccessException {
		try (ComodoBanco bd = new ComodoBanco()) {
			List<Comodo> comodosCompostos = bd.get("comodo_composto");
			
			return comodosCompostos;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
