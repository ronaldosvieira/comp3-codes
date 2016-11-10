package roteiros.quarto;

import entidades.Quarto;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class GuardarQuartoTS {

	public static void execute(String descricao) throws DatabaseAccessException {
		Quarto quarto = new Quarto(descricao);
		
		try (ComodoBanco bd = new ComodoBanco()) {
			bd.insert(quarto);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
