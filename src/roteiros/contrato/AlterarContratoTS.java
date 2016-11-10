package roteiros.contrato;

import entidades.Contrato;
import excecoes.DatabaseAccessException;
import persistencia.ContratoBanco;

public class AlterarContratoTS {

	public static void execute(int id, float comissao) throws DatabaseAccessException {
		Contrato contrato = new Contrato(id, comissao);
		
		try (ContratoBanco bd = new ContratoBanco()) {
			bd.update(id, contrato);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}
	
}