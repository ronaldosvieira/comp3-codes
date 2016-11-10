package roteiros.comodocomposto;

import java.util.ArrayList;
import java.util.List;

import entidades.Comodo;
import entidades.ComodoComposto;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class GuardarComodoCompostoTS {

	public static void execute(String descricao, int[] comodosInt) throws DatabaseAccessException {
	List<Comodo> comodos = new ArrayList<>();
			
		try (ComodoBanco bd = new ComodoBanco()) {
			ComodoComposto comodoComposto = new ComodoComposto(descricao);
			
			for (int comodo : comodosInt) {
				comodos.add(bd.get(comodo));
			}
			
			comodoComposto.alterarComodos(comodos);
			
			bd.insert(comodoComposto);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
