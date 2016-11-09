package roteiros.ambiente;

import entidades.Ambiente;
import excecoes.DatabaseAccessException;
import persistencia.AmbienteBanco;

public class ObterAmbienteTS {
	public static Ambiente execute(int id) throws DatabaseAccessException {
		try (AmbienteBanco bd = new AmbienteBanco()) {
			Ambiente ambiente = bd.get(id);
			
			return ambiente;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}
}
