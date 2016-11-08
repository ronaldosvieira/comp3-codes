package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Mobilia;

public class MobiliaBanco implements AutoCloseable {
	Connection conn;
	
	public MobiliaBanco() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		
		String url = "jdbc:h2:~/decoradora";
		String user = "decoradora";
		String pass = "123456";
		
		conn = DriverManager.getConnection(url, user, pass);
	}
	
	public List<Mobilia> get() throws SQLException {
		String sql = "select * from mobilia";
		ResultSet rs = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		
		List<Mobilia> results = new ArrayList<>();
		
		while (rs.next()) {
			results.add(new Mobilia(
					rs.getInt("id"),
					rs.getString("descricao"),
					rs.getFloat("custo"),
					rs.getInt("tempo_entrega")));
		}
		
		return results;
	}
	
	public Mobilia get(int id) throws SQLException, IndexOutOfBoundsException {
		String sql = "select * "
				+ "from mobilia "
				+ "where id = ?";
		ResultSet rs = null;
		Mobilia mobilia = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		else {
			throw new IndexOutOfBoundsException();
		}
		
		if (rs.next()) {
			mobilia = new Mobilia(
					rs.getInt("id"),
					rs.getString("descricao"),
					rs.getFloat("custo"),
					rs.getInt("tempo_entrega"));
		} else {
			throw new IndexOutOfBoundsException();
		}
		
		return mobilia;
	}
	
	public int insert(Mobilia mobilia) throws SQLException {
		String sql = "insert into mobilia (descricao, custo, tempo_entrega) "
				+ "values (?, ?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, mobilia.obterDescricao());
		stmt.setFloat(2, mobilia.obterCusto());
		stmt.setInt(3, mobilia.obterTempoEntrega());
		
		stmt.executeUpdate();
		
		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				
				return id;
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao inserir mobilia.");
		}
		
		return -1;
	}

	public void update(int id, Mobilia mobilia) throws SQLException {
		String sql = "update mobilia set "
				+ "descricao = ?, "
				+ "custo = ?, "
				+ "tempo_entrega = ? "
				+ "where id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, mobilia.obterDescricao());
		stmt.setFloat(2, mobilia.obterCusto());
		stmt.setInt(3, mobilia.obterTempoEntrega());
		stmt.setInt(4, id);
		
		stmt.executeUpdate();
	}
	
	public boolean remove(int id) throws SQLException {
		String sqlCM = "delete from comodo_mobilia where mobilia_id = ?";
		String sql = "delete from mobilia where id = ?";
		
		try {
			this.get(id);
		} catch (IndexOutOfBoundsException e) {
			throw e;
		}
		
		PreparedStatement stmtCM = conn.prepareStatement(sqlCM);
		stmtCM.setInt(1, id);
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		stmtCM.executeUpdate();
		stmt.executeUpdate();
		
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