package roteiros.sala;

import entidades.Sala;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class GuardarSalaTS {

	public static void execute(String descricao) throws DatabaseAccessException {
		Sala sala = new Sala(descricao);
		
		try (ComodoBanco bd = new ComodoBanco()) {
			bd.insert(sala);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
