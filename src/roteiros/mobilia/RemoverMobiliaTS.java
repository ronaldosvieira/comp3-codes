package roteiros.mobilia;

import excecoes.DatabaseAccessException;
import persistencia.MobiliaBanco;

public class RemoverMobiliaTS {

	public static void execute(int id) throws DatabaseAccessException {
		try (MobiliaBanco bd = new MobiliaBanco()) {
			bd.remove(id);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
