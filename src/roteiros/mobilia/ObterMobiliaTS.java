package roteiros.mobilia;

import entidades.Mobilia;
import excecoes.DatabaseAccessException;
import persistencia.MobiliaBanco;

public class ObterMobiliaTS {

	public static Mobilia execute(int id) throws DatabaseAccessException {
		try (MobiliaBanco bd = new MobiliaBanco()) {
			Mobilia mobilia = bd.get(id);
			
			return mobilia;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
