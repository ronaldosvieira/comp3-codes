package roteiros.mobilia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entidades.Comodo;
import entidades.Mobilia;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;
import persistencia.MobiliaBanco;

public class AlterarMobiliaTS {

	public static void execute(int id, String descricao, Float custo, int tempoEntrega, List<String> comodos) 
			throws DatabaseAccessException {
		List<Comodo> oldComodos = new ArrayList<>();
		
		Mobilia mobilia = new Mobilia(id, descricao, custo, tempoEntrega);
		
		try (MobiliaBanco bd = new MobiliaBanco();
				ComodoBanco comodoBd = new ComodoBanco()) {
			bd.update(id, mobilia);
			
			oldComodos = comodoBd.get().stream()
					.filter(c -> c.listaMobiliaDisponivel().contains(mobilia))
					.collect(Collectors.toList());
			
			for (String comodoStr : comodos) {
				Comodo comodo = comodoBd.get(Integer.parseInt(comodoStr));
				
				comodo.associarMobilia(mobilia);
				
				comodoBd.update(comodo.obterId(), comodo);
			}
			
			for (Comodo comodo : oldComodos) {
				if (!comodos.contains(String.valueOf(comodo.obterId()))) {
					comodo.desassociarMobilia(mobilia);
					
					comodoBd.update(comodo.obterId(), comodo);
				}
			}
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
