package roteiros.cozinha;

import entidades.Cozinha;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class GuardarCozinhaTS {

	public static void execute(String descricao) throws DatabaseAccessException {
		Cozinha cozinha = new Cozinha(descricao);
		
		try (ComodoBanco bd = new ComodoBanco()) {
			bd.insert(cozinha);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
