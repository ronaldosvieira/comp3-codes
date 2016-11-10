package roteiros.contrato;

import excecoes.DatabaseAccessException;
import persistencia.ContratoBanco;

public class RemoverContratoTS {

	public static void execute(int id) throws DatabaseAccessException {
		try (ContratoBanco bd = new ContratoBanco()) {
			bd.remove(id);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
