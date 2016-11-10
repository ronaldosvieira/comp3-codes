package roteiros.mobilia;

import entidades.Comodo;
import entidades.Mobilia;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;
import persistencia.MobiliaBanco;

public class GuardarMobiliaTS {

	public static void execute(String descricao, Float custo, int tempoEntrega, int[] comodosInt) 
			throws DatabaseAccessException {
		try (MobiliaBanco bd = new MobiliaBanco();
				ComodoBanco comodoBd = new ComodoBanco()) {
			Mobilia mobilia = new Mobilia(descricao, custo, tempoEntrega);
			
			int id = bd.insert(mobilia);
			
			mobilia = new Mobilia(id, 
					mobilia.obterDescricao(), 
					mobilia.obterCusto(), 
					mobilia.obterTempoEntrega());
			
			for (int comodoStr : comodosInt) {
				Comodo comodo = comodoBd.get(comodoStr);
				
				comodo.associarMobilia(mobilia);
				
				comodoBd.update(comodo.obterId(), comodo);
			}
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
