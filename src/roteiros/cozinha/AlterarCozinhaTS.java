package roteiros.cozinha;

import entidades.Cozinha;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class AlterarCozinhaTS {

	public static void execute(int id, String descricao) throws DatabaseAccessException {
		try (ComodoBanco bd = new ComodoBanco()) {
			Cozinha cozinha = (Cozinha) bd.get(id);
			
			cozinha.alterarDescricao(descricao);
			
			bd.update(id, cozinha);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
