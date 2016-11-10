package roteiros.comodocomposto;

import java.util.ArrayList;
import java.util.List;

import entidades.Comodo;
import entidades.ComodoComposto;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class AlterarComodoCompostoTS {

	public static void execute(int id, String descricao, int[] comodosInt) 
			throws DatabaseAccessException {
		List<Comodo> comodos = new ArrayList<>();
		
		try (ComodoBanco bd = new ComodoBanco()) {
			ComodoComposto comodoComposto = (ComodoComposto) bd.get(id);
			
			for (int comodo : comodosInt) {
				comodos.add(bd.get(comodo));
			}
			
			comodoComposto.alterarDescricao(descricao);
			comodoComposto.alterarComodos(comodos);
			
			bd.update(id, comodoComposto);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}	
	}

}
