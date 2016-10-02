package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Sala;

public class SalaBanco implements AutoCloseable {
	Connection conn;
	
	public SalaBanco() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		
		String url = "jdbc:h2:~/decoradora";
		String user = "decoradora";
		String pass = "123456";
		
		conn = DriverManager.getConnection(url, user, pass);
	}
	
	public List<Sala> get() throws SQLException {
		String sql = "select * "
				+ "from comodo "
				+ "join sala on id = comodo_id";
		ResultSet rs = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		
		List<Sala> results = new ArrayList<>();
		
		while (rs.next()) {
			results.add(new Sala(
					rs.getInt("id"),
					rs.getString("descricao")));
		}
		
		return results;
	}
	
	public Sala get(int id) throws SQLException, IndexOutOfBoundsException {
		String sql = "select * "
				+ "from comodo "
				+ "join sala on id = comodo_id "
				+ "where id = ?";
		ResultSet rs = null;
		Sala sala = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		else {
			throw new IndexOutOfBoundsException();
		}
		
		if (rs.next()) {
			sala = new Sala(rs.getInt("id"), rs.getString("descricao"));
		} else {
			throw new IndexOutOfBoundsException();
		}
		
		return sala;
	}
	
	public int insert(Sala sala) throws SQLException {
		String sql1 = "insert into comodo (descricao) values (?)";
		String sql2 = "insert into sala (comodo_id) values (?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, sala.obterDescricao());
		
		stmt.executeUpdate();
		
		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				
				PreparedStatement stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, id);
				
				stmt2.executeUpdate();
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao inserir sala.");
		}
		
		return -1;
	}

	public void update(int id, Sala sala) throws SQLException {
		String sql = "update comodo set descricao = ? where id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, sala.obterDescricao());
		stmt.setInt(2, id);
		
		stmt.executeUpdate();
	}
	
	public boolean remove(int id) throws SQLException {
		String sql = "delete from sala where comodo_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, String.valueOf(id));
		
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
