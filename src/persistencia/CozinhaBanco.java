package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Cozinha;

public class CozinhaBanco implements AutoCloseable {
	Connection conn;
	
	public CozinhaBanco() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		
		String url = "jdbc:h2:~/decoradora";
		String user = "decoradora";
		String pass = "123456";
		
		conn = DriverManager.getConnection(url, user, pass);
	}
	
	public List<Cozinha> get() throws SQLException {
		String sql = "select * "
				+ "from comodo "
				+ "join cozinha on id = comodo_id";
		ResultSet rs = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		
		List<Cozinha> results = new ArrayList<>();
		
		while (rs.next()) {
			results.add(new Cozinha(
					rs.getInt("id"),
					rs.getString("descricao")));
		}
		
		stmt.close();
		
		return results;
	}
	
	public Cozinha get(int id) throws SQLException, IndexOutOfBoundsException {
		String sql = "select * "
				+ "from comodo "
				+ "join cozinha on id = comodo_id "
				+ "where id = ?";
		ResultSet rs = null;
		Cozinha cozinha = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		else {
			throw new IndexOutOfBoundsException();
		}
		
		if (rs.next()) {
			cozinha = new Cozinha(rs.getInt("id"), rs.getString("descricao"));
		} else {
			throw new IndexOutOfBoundsException();
		}
		
		return cozinha;
	}
	
	public int insert(Cozinha cozinha) throws SQLException {
		String sql1 = "insert into comodo (descricao) values (?)";
		String sql2 = "insert into cozinha (comodo_id) values (?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, cozinha.obterDescricao());
		
		stmt.executeUpdate();
		
		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				
				PreparedStatement stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, id);
				
				stmt2.executeUpdate();
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao inserir cozinha.");
		}
		
		return -1;
	}

	public void update(int id, Cozinha cozinha) throws SQLException {
		String sql = "update comodo set descricao = ? where id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, cozinha.obterDescricao());
		stmt.setInt(2, id);
		
		// add relacionamento com mobilias
		
		stmt.executeUpdate();
	}
	
	public boolean remove(int id) throws SQLException {
		String sql = "delete from cozinha where comodo_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, String.valueOf(id));
		
		stmt.execute();
		
		int affectedRows = stmt.getUpdateCount();
		
		stmt.close();
		
		return affectedRows == -1? false : true;
	}
	
	public void close() throws SQLException {
		if (!conn.isClosed()) {
			conn.rollback();
			conn.close();
		}
	}
}
