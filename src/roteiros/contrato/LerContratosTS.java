package roteiros.contrato;

import java.util.List;

import entidades.Contrato;
import excecoes.DatabaseAccessException;
import persistencia.ContratoBanco;

public class LerContratosTS {

	public static List<Contrato> execute() throws DatabaseAccessException {
		List<Contrato> contratos = null;
		
		try (ContratoBanco bd = new ContratoBanco()) {
			contratos = bd.get();
			
			return contratos;
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
