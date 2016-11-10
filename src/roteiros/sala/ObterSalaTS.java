package roteiros.sala;

import entidades.Sala;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class ObterSalaTS {

	public static Sala execute(int id) throws DatabaseAccessException {
		try (ComodoBanco bd = new ComodoBanco()) {
			Sala sala = (Sala) bd.get(id);
			
			return sala;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
