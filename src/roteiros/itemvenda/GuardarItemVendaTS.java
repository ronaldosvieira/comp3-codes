package roteiros.itemvenda;

import entidades.ItemVenda;
import excecoes.DatabaseAccessException;
import persistencia.ItemVendaBanco;
import persistencia.MobiliaBanco;

public class GuardarItemVendaTS {

	public static void execute(int quantidade, int mobiliaId, int ambienteId) 
			throws DatabaseAccessException {
		try (ItemVendaBanco bd = new ItemVendaBanco();
				MobiliaBanco mobiliaBd = new MobiliaBanco()) {
			ItemVenda itemVenda = new ItemVenda(quantidade, mobiliaBd.get(mobiliaId), ambienteId);
			
			int id = bd.insert(itemVenda);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
