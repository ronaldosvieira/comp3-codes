package roteiros.mobilia;

import java.util.List;

import entidades.Mobilia;
import excecoes.DatabaseAccessException;
import persistencia.MobiliaBanco;

public class LerMobiliaTS {

	public static List<Mobilia> execute() throws DatabaseAccessException {
		List<Mobilia> mobilias = null;
		
		try (MobiliaBanco bd = new MobiliaBanco()) {
			mobilias = bd.get();
			
			return mobilias;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
