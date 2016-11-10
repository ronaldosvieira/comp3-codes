package roteiros.cozinha;

import java.sql.SQLException;

import entidades.Cozinha;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;

public class ObterCozinhaTS {

	public static Cozinha execute(int id) throws DatabaseAccessException {
		try (ComodoBanco bd = new ComodoBanco()) {
			Cozinha cozinha = (Cozinha) bd.get(id);
			
			return cozinha;
		} catch (SQLException | ClassNotFoundException e) {
			throw new DatabaseAccessException("Erro ao acessar o banco de dados");
		} catch (IndexOutOfBoundsException e) {
			throw e;
		} catch (ClassCastException e) {
			throw e;
		}
	}

}
