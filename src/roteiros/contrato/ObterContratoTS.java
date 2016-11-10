package roteiros.contrato;

import entidades.Contrato;
import excecoes.DatabaseAccessException;
import persistencia.ContratoBanco;

public class ObterContratoTS {

	public static Contrato execute(int id) throws DatabaseAccessException {
		try (ContratoBanco bd = new ContratoBanco()) {
			Contrato contrato = bd.get(id);
			
			return contrato;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
