package roteiros.contrato;

import entidades.Contrato;
import excecoes.DatabaseAccessException;
import persistencia.ContratoBanco;

public class GuardarContratoTS {

	public static void execute(Float comissao) throws DatabaseAccessException {
		Contrato contrato = new Contrato(comissao);
		
		try (ContratoBanco bd = new ContratoBanco()) {
			bd.insert(contrato);
		} catch (Exception e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		}
	}

}
