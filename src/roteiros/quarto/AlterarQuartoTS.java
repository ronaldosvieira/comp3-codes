package roteiros.quarto;

import entidades.Quarto;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class AlterarQuartoTS {

	public static void execute(int id, String descricao) throws DatabaseAccessException {
		try (ComodoBanco bd = new ComodoBanco()) {
			Quarto quarto = (Quarto) bd.get(id);
			
			quarto.alterarDescricao(descricao);
			
			bd.update(id, quarto);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
