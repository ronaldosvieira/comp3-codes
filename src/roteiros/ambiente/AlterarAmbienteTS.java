package roteiros.ambiente;

import entidades.Ambiente;
import excecoes.DatabaseAccessException;
import persistencia.AmbienteBanco;

public class AlterarAmbienteTS {
	public static void execute(int id, int numParedes, int numPortas, float metragem, int contratoId) 
			throws DatabaseAccessException {
		Ambiente ambiente = new Ambiente(id, numParedes, numPortas, metragem, contratoId);
		
		try (AmbienteBanco bd = new AmbienteBanco()) {
			bd.update(id, ambiente);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}
}
