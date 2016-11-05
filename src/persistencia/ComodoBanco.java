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
import entidades.ComodoComposto;
import entidades.Mobilia;
import entidades.Sala;

public class ComodoBanco implements AutoCloseable {
	Connection conn;

	public ComodoBanco() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");

		String url = "jdbc:h2:~/decoradora";
		String user = "decoradora";
		String pass = "123456";

		conn = DriverManager.getConnection(url, user, pass);
	}

	public List<Comodo> get(String tipo) throws SQLException {
		String sql1 = "select * from comodo";
		if (tipo != null) sql1 += " where tipo = '" + tipo + "'";
		String sql2 = "select m.* from mobilia m " + "join comodo_mobilia cm on cm.mobilia_id = m.id "
				+ "where cm.comodo_id = ?";
		String sqlCC = "select * from comodo_composto_comodo ccc "
				+ "join comodo_composto cc on ccc.comodo_composto_id = cc.comodo_id " + "where cc.comodo_id = ?";
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rsCC = null;

		PreparedStatement stmt1 = conn.prepareStatement(sql1);
		PreparedStatement stmt2 = conn.prepareStatement(sql2);
		PreparedStatement stmtCC = conn.prepareStatement(sqlCC);

		if (stmt1.execute())
			rs = stmt1.getResultSet();

		List<Comodo> results = new ArrayList<>();

		while (rs.next()) {
			List<Mobilia> mobilias = new ArrayList<>();

			stmt2.setInt(1, rs.getInt("id"));

			if (stmt2.execute())
				rs2 = stmt2.getResultSet();

			while (rs2.next()) {
				mobilias.add(
						new Mobilia(rs2.getString("descricao"), rs2.getFloat("custo"), rs2.getInt("tempo_entrega")));
			}

			Comodo comodo = Comodo.create(rs.getInt("id"), rs.getString("descricao"), rs.getString("tipo"), mobilias);

			if (comodo.obterTipo().equals("comodo_composto")) {
				List<Comodo> comodos = new ArrayList<>();

				stmtCC.setInt(1, rs.getInt("id"));

				if (stmtCC.execute())
					rsCC = stmtCC.getResultSet();

				while (rsCC.next()) {
					comodos.add(this.get(rsCC.getInt("comodo_id")));
				}

				((ComodoComposto) comodo).alterarComodos(comodos);
			}

			results.add(comodo);
		}

		return results;
	}

	public List<Comodo> get() throws SQLException {
		return this.get(null);
	}

	public Comodo get(int id) throws SQLException, IndexOutOfBoundsException {
		String sql1 = "select * from comodo where id = ?";
		String sql2 = "select m.* from mobilia m " + "join comodo_mobilia cm on cm.mobilia_id = m.id "
				+ "where cm.comodo_id = ?";
		ResultSet rs = null;
		ResultSet rs2 = null;
		Comodo comodo = null;
		List<Mobilia> mobilias = new ArrayList<>();

		PreparedStatement stmt1 = conn.prepareStatement(sql1);
		PreparedStatement stmt2 = conn.prepareStatement(sql2);

		stmt1.setInt(1, id);

		if (stmt1.execute())
			rs = stmt1.getResultSet();
		else
			throw new IndexOutOfBoundsException();

		if (rs.next()) {
			stmt2.setInt(1, rs.getInt("id"));

			if (stmt2.execute())
				rs2 = stmt2.getResultSet();

			while (rs2.next()) {
				mobilias.add(
						new Mobilia(rs2.getString("descricao"), rs2.getFloat("custo"), rs2.getInt("tempo_entrega")));
			}

			comodo = Comodo.create(rs.getInt("id"), rs.getString("descricao"), rs.getString("tipo"), mobilias);
		} else {
			throw new IndexOutOfBoundsException();
		}

		return comodo;
	}

	public int insert(Comodo comodo) throws SQLException {
		String sql1 = "insert into comodo (descricao, tipo) values (?, ?)";
		String sql2 = "insert into " + comodo.obterTipo() + " (comodo_id) values (?)";
		String sql3 = "insert into comodo_mobilia (comodo_id, mobilia_id) values (?, ?)";

		PreparedStatement stmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, comodo.obterDescricao());
		stmt.setString(2, comodo.obterTipo());

		stmt.executeUpdate();

		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);

				PreparedStatement stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, id);

				stmt2.executeUpdate();

				PreparedStatement stmt3 = conn.prepareStatement(sql3);

				for (Mobilia mobilia : comodo.listaMobiliaDisponivel()) {
					stmt3.setInt(1, id);
					stmt3.setInt(2, mobilia.obterId());

					stmt3.executeUpdate();
				}

				return id;
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao inserir comodo.");
		}

		return -1;
	}

	public void update(int id, Comodo comodo) throws SQLException {
		String sql1 = "update comodo set descricao = ? where id = ?";
		String sql2 = "select * from comodo_mobilia where comodo_id = ?";
		String sqlInsert = "insert into comodo_mobilia (comodo_id, mobilia_id) values (?, ?)";
		String sqlRemove = "delete from comodo_mobilia where comodo_id = ? and mobilia_id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql1);
		stmt.setString(1, comodo.obterDescricao());
		stmt.setInt(2, id);

		stmt.executeUpdate();

		PreparedStatement stmt2 = conn.prepareStatement(sql2);
		stmt2.setInt(1, id);

		PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);
		PreparedStatement stmtRemove = conn.prepareStatement(sqlRemove);

		ResultSet rs2 = null;
		List<Integer> mobiliaIds = new ArrayList<>();

		if (stmt2.execute()) {
			rs2 = stmt2.getResultSet();
		}

		// obtem lista de mobilias associadas atualmente
		while (rs2.next()) {
			mobiliaIds.add(rs2.getInt("mobilia_id"));
		}

		// associa as mobilias necessarias
		for (Mobilia mobilia : comodo.listaMobiliaDisponivel()) {
			if (!mobiliaIds.contains(mobilia.obterId())) {
				stmtInsert.setInt(1, comodo.obterId());
				stmtInsert.setInt(2, mobilia.obterId());

				stmtInsert.executeUpdate();
			}
		}

		// desassocia as mobilias necessarias
		for (int mobiliaId : mobiliaIds) {
			if (!comodo.listaMobiliaDisponivel().contains(mobiliaId)) {
				stmtRemove.setInt(1, comodo.obterId());
				stmtRemove.setInt(2, mobiliaId);
			}
		}
	}

	public void remove(int id) throws SQLException, IndexOutOfBoundsException {
		Comodo comodo = this.get(id);

		String sql1 = "delete from comodo_mobilia where comodo_id = ?";
		String sqlCC = "delete from comodo_composto_comodo where comodo_composto_id = ?";
		String sql2 = "delete from " + comodo.obterTipo() + " where comodo_id = ?";
		String sql3 = "delete from comodo where id = ?";

		if (comodo.obterTipo().equals("comodo_composto")) {
			PreparedStatement stmtCC = conn.prepareStatement(sqlCC);
			stmtCC.setInt(1, comodo.obterId());
		}

		PreparedStatement stmt = conn.prepareStatement(sql1);
		stmt.setInt(1, id);

		stmt.executeUpdate();

		PreparedStatement stmt2 = conn.prepareStatement(sql2);
		stmt2.setInt(1, comodo.obterId());

		stmt2.executeUpdate();

		PreparedStatement stmt3 = conn.prepareStatement(sql3);
		stmt3.setInt(1, comodo.obterId());

		stmt3.executeUpdate();

		int affectedRows = stmt.getUpdateCount();
	}

	public void close() throws SQLException {
		if (!conn.isClosed()) {
			conn.rollback();
			conn.close();
		}
	}
}
