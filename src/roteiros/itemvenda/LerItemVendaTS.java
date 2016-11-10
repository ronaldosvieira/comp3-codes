package roteiros.itemvenda;

import java.util.List;

import entidades.ItemVenda;
import excecoes.DatabaseAccessException;
import persistencia.ItemVendaBanco;

public class LerItemVendaTS {

	public static List<ItemVenda> execute(int ambienteId) throws DatabaseAccessException {
		try (ItemVendaBanco bd = new ItemVendaBanco()) {
			List<ItemVenda> itemVendas = bd.getWhereAmbienteId(ambienteId);
			
			return itemVendas;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
