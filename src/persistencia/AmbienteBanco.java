package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Ambiente;

public class AmbienteBanco implements AutoCloseable {
	Connection conn;
	
	public AmbienteBanco() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		
		String url = "jdbc:h2:~/decoradora";
		String user = "decoradora";
		String pass = "123456";
		
		conn = DriverManager.getConnection(url, user, pass);
	}
	
	public List<Ambiente> get() throws SQLException {
		String sql = "select * from ambiente";
		ResultSet rs = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		
		List<Ambiente> results = new ArrayList<>();
		
		while (rs.next()) {
			results.add(new Ambiente(
					rs.getInt("id"),
					rs.getInt("num_paredes"),
					rs.getInt("num_portas"),
					rs.getFloat("metragem")));
		}
		
		return results;
	}
	
	public Ambiente get(int id) throws SQLException, IndexOutOfBoundsException {
		String sql = "select * "
				+ "from ambiente "
				+ "where id = ?";
		ResultSet rs = null;
		Ambiente ambiente = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		else {
			throw new IndexOutOfBoundsException();
		}
		
		if (rs.next()) {
			ambiente = new Ambiente(
					rs.getInt("id"),
					rs.getInt("num_paredes"),
					rs.getInt("num_portas"),
					rs.getFloat("metragem"));
		} else {
			throw new IndexOutOfBoundsException();
		}
		
		return ambiente;
	}
	
	public int insert(Ambiente ambiente) throws SQLException {
		String sql = "insert into ambiente (num_paredes, num_portas, metragem) "
				+ "values (?, ?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, ambiente.obterNumParedes());
		stmt.setInt(2, ambiente.obterNumPortas());
		stmt.setFloat(3, ambiente.obterMetragem());
		
		stmt.executeUpdate();
		
		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				
				return id;
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao inserir ambiente.");
		}
		
		return -1;
	}

	public void update(int id, Ambiente ambiente) throws SQLException {
		String sql = "update ambiente set "
				+ "descricao = ?, "
				+ "custo = ?, "
				+ "tempo_entrega = ? "
				+ "where id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, ambiente.obterNumParedes());
		stmt.setInt(2, ambiente.obterNumPortas());
		stmt.setFloat(3, ambiente.obterMetragem());
		stmt.setInt(4, id);
		
		stmt.executeUpdate();
	}
	
	public boolean remove(int id) throws SQLException {
		String sql = "delete from ambiente where id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		stmt.execute();
		
		int affectedRows = stmt.getUpdateCount();
		
		return affectedRows == -1? false : true;
	}
	
	public void close() throws SQLException {
		if (!conn.isClosed()) {
			conn.rollback();
			conn.close();
		}
	}
}