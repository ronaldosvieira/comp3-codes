package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Comodo;
import entidades.Mobilia;

public class ComodoBanco implements AutoCloseable {
	Connection conn;

	public ComodoBanco() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");

		String url = "jdbc:h2:~/decoradora";
		String user = "decoradora";
		String pass = "123456";

		conn = DriverManager.getConnection(url, user, pass);
	}

	public List<Comodo> get() throws SQLException {
		String sql1 = "select * from comodo";
		String sql2 = "select m.* from mobilia m " + 
				"join comodo_mobilia cm on cm.mobilia_id = m.id " + 
				"where cm.comodo_id = ?";
		ResultSet rs = null;
		ResultSet rs2 = null;

		PreparedStatement stmt1 = conn.prepareStatement(sql1);
		PreparedStatement stmt2 = conn.prepareStatement(sql2);

		if (stmt1.execute())
			rs = stmt1.getResultSet();

		List<Comodo> results = new ArrayList<>();

		while (rs.next()) {
			List<Mobilia> mobilias = new ArrayList<>();
			
			stmt2.setInt(1, rs.getInt("id"));
			
			if (stmt2.execute()) rs2 = stmt2.getResultSet();
			
			while (rs2.next()) {
				mobilias.add(new Mobilia(
						rs2.getString("descricao"), 
						rs2.getFloat("custo"), 
						rs2.getInt("tempo_entrega")));
			}
			
			results.add(Comodo.create(
					rs.getInt("id"), 
					rs.getString("descricao"), 
					rs.getString("tipo"),
					mobilias));
		}

		return results;
	}

	public Comodo get(int id) throws SQLException, IndexOutOfBoundsException {
		String sql = "select * " + "from comodo " + "join comodo on id = comodo_id " + "where id = ?";
		ResultSet rs = null;
		Comodo comodo = null;

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);

		if (stmt.execute())
			rs = stmt.getResultSet();
		else {
			throw new IndexOutOfBoundsException();
		}

		if (rs.next()) {
			comodo = new Comodo(rs.getInt("id"), rs.getString("descricao"));
		} else {
			throw new IndexOutOfBoundsException();
		}

		return comodo;
	}

	public int insert(Comodo comodo) throws SQLException {
		String sql1 = "insert into comodo (descricao, tipo) values (?, ?)";
		String sql2 = "insert into ? (comodo_id) values (?)";

		PreparedStatement stmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, comodo.obterDescricao());
		stmt.setString(2, comodo.obterTipo());

		stmt.executeUpdate();

		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);

				PreparedStatement stmt2 = conn.prepareStatement(sql2);
				stmt2.setString(1, comodo.obterTipo());
				stmt2.setInt(2, id);

				stmt2.executeUpdate();

				return id;
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao inserir comodo.");
		}

		return -1;
	}

	public void update(int id, Comodo comodo) throws SQLException {
		String sql = "update comodo set descricao = ? where id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, comodo.obterDescricao());
		stmt.setInt(2, id);

		stmt.executeUpdate();
	}

	public boolean remove(int id) throws SQLException {
		String sql = "delete from comodo where comodo_id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, String.valueOf(id));

		stmt.execute();

		int affectedRows = stmt.getUpdateCount();

		return affectedRows == -1 ? false : true;
	}

	public void close() throws SQLException {
		if (!conn.isClosed()) {
			conn.rollback();
			conn.close();
		}
	}
}
