package roteiros.ambiente;

import java.util.List;

import entidades.Ambiente;
import excecoes.DatabaseAccessException;
import persistencia.AmbienteBanco;

public class LerAmbienteTS {

	public static List<Ambiente> execute(int contratoId) throws DatabaseAccessException {
		try (AmbienteBanco bd = new AmbienteBanco()) {
			List<Ambiente> ambientes = bd.getWhereContratoId(contratoId);
			
			return ambientes;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
