package roteiros.quarto;

import entidades.Quarto;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class ObterQuartoTS {

	public static Quarto execute(int id) throws DatabaseAccessException {
		try (ComodoBanco bd = new ComodoBanco()) {
			Quarto quarto = (Quarto) bd.get(id);
			
			return quarto;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
