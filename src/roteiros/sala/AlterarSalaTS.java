package roteiros.sala;

import entidades.Sala;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class AlterarSalaTS {

	public static void execute(int id, String descricao) throws DatabaseAccessException {
		Sala sala = null;
		
		try (ComodoBanco bd = new ComodoBanco()) {
			sala = (Sala) bd.get(id);
			
			sala.alterarDescricao(descricao);
			
			bd.update(id, sala);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
