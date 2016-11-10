package roteiros.comodocomposto;

import entidades.ComodoComposto;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class ObterComodoCompostoTS {

	public static ComodoComposto execute(int id) throws DatabaseAccessException {
		try (ComodoBanco bd = new ComodoBanco()) {
			ComodoComposto comodoComposto = (ComodoComposto) bd.get(id);
			
			return comodoComposto;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
