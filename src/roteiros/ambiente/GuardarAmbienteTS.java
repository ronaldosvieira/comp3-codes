package roteiros.ambiente;

import entidades.Ambiente;
import excecoes.DatabaseAccessException;
import persistencia.AmbienteBanco;

public class GuardarAmbienteTS {
	public static void execute(int numParedes, int numPortas, float metragem, int contratoId) 
			throws DatabaseAccessException {
		Ambiente ambiente = new Ambiente(numParedes, numPortas, metragem, contratoId);
		
		try (AmbienteBanco bd = new AmbienteBanco()) {
			bd.insert(ambiente.obterContratoId(), ambiente);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}
}
