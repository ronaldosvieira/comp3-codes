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
import entidades.Contrato;

public class ContratoBanco implements AutoCloseable {
	Connection conn;
	AmbienteBanco bd;
	
	public ContratoBanco() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		
		String url = "jdbc:h2:~/decoradora";
		String user = "decoradora";
		String pass = "123456";
		
		conn = DriverManager.getConnection(url, user, pass);
		
		bd = new AmbienteBanco();
	}
	
	public List<Contrato> get() throws SQLException {
		String sql = "select * "
				+ "from contrato";
		String sql2 = "select * from ambiente where contrato_id = ?";
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		
		List<Contrato> results = new ArrayList<>();
		
		while (rs.next()) {
			Contrato contrato = new Contrato(
					rs.getInt("id"),
					rs.getFloat("comissao"));
			
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, contrato.obterId());
			
			if (stmt2.execute()) rs2 = stmt2.getResultSet();
			
			while (rs2.next()) {
				contrato.inserirAmbiente(bd.get(rs2.getInt("id")));
			}
			
			results.add(contrato);
		}
		
		return results;
	}
	
	public Contrato get(int id) throws SQLException, IndexOutOfBoundsException {
		String sql = "select * "
				+ "from contrato "
				+ "where id = ?";
		String sql2 = "select * from ambiente where contrato_id = ?";
		ResultSet rs = null;
		ResultSet rs2 = null;
		Contrato contrato = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		else {
			throw new IndexOutOfBoundsException();
		}
		
		if (rs.next()) {
			contrato = new Contrato(rs.getInt("id"), rs.getFloat("comissao"));
			
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, contrato.obterId());
			
			if (stmt2.execute()) rs2 = stmt2.getResultSet();
			
			while (rs2.next()) {
				contrato.inserirAmbiente(bd.get(rs2.getInt("id")));
			}
		} else {
			throw new IndexOutOfBoundsException();
		}
		
		return contrato;
	}
	
	public int insert(Contrato contrato) throws SQLException {
		String sql = "insert into contrato (comissao) "
				+ "values (?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setFloat(1, contrato.obterComissao());
		
		stmt.executeUpdate();
		
		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				
				for (Ambiente ambiente : contrato.obterAmbientes()) {
					bd.insert(id, ambiente);
				}
				
				return id;
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao inserir ambiente.");
		}
		
		return -1;
	}

	public void update(int id, Contrato contrato) throws SQLException {
		String sql = "update contrato set comissao = ? where id = ?";
		String sql2 = "select * from ambiente where contrato_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setFloat(1, contrato.obterComissao());
		stmt.setInt(2, id);
		
		stmt.executeUpdate();
		
		List<Ambiente> ambientes = new ArrayList<>();
		ResultSet rs2 = null;
		
		PreparedStatement stmt2 = conn.prepareStatement(sql2);
		stmt2.setInt(1, contrato.obterId());
		
		if (stmt2.execute()) rs2 = stmt2.getResultSet();
		
		while (rs2.next()) {
			ambientes.add(bd.get(rs2.getInt("id")));
		}
		
		for (Ambiente ambiente : contrato.obterAmbientes()) {
			if (!ambientes.contains(ambiente)) {
				bd.insert(contrato.obterId(), ambiente);
			}
		}
		
		for (Ambiente ambiente : ambientes) {
			if (!contrato.obterAmbientes().contains(ambiente)) {
				bd.remove(ambiente.obterId());
			}
		}
	}
	
	public boolean remove(int id) throws SQLException {
		String sql = "delete from contrato where id = ?";
		String sql2 = "select * from ambiente where contrato_id = ?";
		ResultSet rs2 = null;
		
		PreparedStatement stmt2 = conn.prepareStatement(sql2);
		stmt2.setInt(1, id);
		if (stmt2.execute()) rs2 = stmt2.getResultSet();
		
		while (rs2.next()) {
			bd.remove(rs2.getInt("id"));
		}
		
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
		
		bd.close();
	}
}