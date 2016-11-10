package roteiros.itemvenda;

import entidades.ItemVenda;
import excecoes.DatabaseAccessException;
import persistencia.ItemVendaBanco;

public class ObterItemVendaTS {

	public static ItemVenda execute(int id) throws DatabaseAccessException {
		try (ItemVendaBanco bd = new ItemVendaBanco()) {
			ItemVenda item = bd.get(id);

			return item;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
