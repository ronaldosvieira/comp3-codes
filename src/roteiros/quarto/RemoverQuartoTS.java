package roteiros.quarto;

import entidades.Comodo;
import entidades.ComodoComposto;
import excecoes.BusinessLogicException;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class RemoverQuartoTS {

	public static void execute(int id) throws DatabaseAccessException, BusinessLogicException {
		try (ComodoBanco bd = new ComodoBanco()) {
			Comodo comodo = bd.get(id);
			
			if (!comodo.listaMobiliaDisponivel().isEmpty()) {
				throw new BusinessLogicException("O cômodo '" + comodo.obterDescricao() + 
						"' não pode ser removido, pois, existem mobílias associadas a ele.");
			}
			
			for (Comodo comodoComposto : bd.get("comodo_composto")) {
				if (((ComodoComposto) comodoComposto).obterComodos().contains(comodo)) {
					throw new BusinessLogicException("O cômodo '" + comodo.obterDescricao() + 
							"' não pode ser removido, pois, existem cômodos compostos associados a ele.");
				}
			}
			
			bd.remove(id);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
