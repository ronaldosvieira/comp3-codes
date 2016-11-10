package roteiros.itemvenda;

import excecoes.DatabaseAccessException;
import persistencia.ItemVendaBanco;

public class AlterarItemVendaTS {

	public static void execute(int id, int quantidade) throws DatabaseAccessException {
		try (ItemVendaBanco bd = new ItemVendaBanco()) {
			bd.update(id, quantidade);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
