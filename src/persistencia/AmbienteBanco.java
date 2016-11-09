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
import entidades.ItemVenda;

public class AmbienteBanco implements AutoCloseable {
	Connection conn;
	private ItemVendaBanco bd;
	
	public AmbienteBanco() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		
		String url = "jdbc:h2:~/decoradora";
		String user = "decoradora";
		String pass = "123456";
		
		conn = DriverManager.getConnection(url, user, pass);
		
		bd = new ItemVendaBanco();
	}
	
	public List<Ambiente> get() throws SQLException {
		String sql = "select * from ambiente";
		ResultSet rs = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		
		List<Ambiente> results = new ArrayList<>();
		
		while (rs.next()) {
			Ambiente ambiente = new Ambiente(
					rs.getInt("id"),
					rs.getInt("num_paredes"),
					rs.getInt("num_portas"),
					rs.getFloat("metragem"),
					rs.getInt("contrato_id"));
			
			List<ItemVenda> itens = bd.getWhereAmbienteId(ambiente.obterId());
			
			for (ItemVenda item : itens) {
				ambiente.inserirItemVenda(item);
			}
			
			results.add(ambiente);
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
					rs.getFloat("metragem"),
					rs.getInt("contrato_id"));
			
			List<ItemVenda> itens = bd.getWhereAmbienteId(ambiente.obterId());
			
			for (ItemVenda item : itens) {
				ambiente.inserirItemVenda(item);
			}
		} else {
			throw new IndexOutOfBoundsException();
		}
		
		return ambiente;
	}
	
	public List<Ambiente> getWhereContratoId(int contratoId) throws SQLException, IndexOutOfBoundsException {
		String sql = "select * "
				+ "from ambiente "
				+ "where contrato_id = ?";
		ResultSet rs = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, contratoId);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		else {
			throw new IndexOutOfBoundsException();
		}
		
		List<Ambiente> results = new ArrayList<>();
		
		while (rs.next()) {
			Ambiente ambiente = new Ambiente(
					rs.getInt("id"),
					rs.getInt("num_paredes"),
					rs.getInt("num_portas"),
					rs.getFloat("metragem"),
					rs.getInt("contrato_id"));
			
			List<ItemVenda> itens = bd.getWhereAmbienteId(ambiente.obterId());
			
			for (ItemVenda item : itens) {
				ambiente.inserirItemVenda(item);
			}
			
			results.add(ambiente);
		}
		
		return results;
	}
	
	
	public int insert(int contratoId, Ambiente ambiente) throws SQLException {
		String sql = "insert into ambiente (contrato_id, num_paredes, num_portas, metragem) "
				+ "values (?, ?, ?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, contratoId);
		stmt.setInt(2, ambiente.obterNumParedes());
		stmt.setInt(3, ambiente.obterNumPortas());
		stmt.setFloat(4, ambiente.obterMetragem());
		
		stmt.executeUpdate();
		
		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				
				for (ItemVenda item : ambiente.obterItens()) {
					bd.insert(item);
				}
				
				return id;
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao inserir ambiente.");
		}
		
		return -1;
	}

	public void update(int id, Ambiente ambiente) throws SQLException {
		String sql = "update ambiente set "
				+ "num_paredes = ?, "
				+ "num_portas = ?, "
				+ "metragem = ? "
				+ "where id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, ambiente.obterNumParedes());
		stmt.setInt(2, ambiente.obterNumPortas());
		stmt.setFloat(3, ambiente.obterMetragem());
		stmt.setInt(4, id);
		
		stmt.executeUpdate();
		
		List<ItemVenda> itens = bd.getWhereAmbienteId(id);
		
		for (ItemVenda item : ambiente.obterItens()) {
			if (!itens.contains(item)) {
				bd.insert(item);
			}
		}
		
		for (ItemVenda item : itens) {
			if (!itens.contains(item)) {
				bd.remove(item.obterId());
			}
		}
	}
	
	public boolean remove(int id) throws SQLException {
		String sql = "delete from ambiente where id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		List<ItemVenda> itens = bd.getWhereAmbienteId(id);
		
		for (ItemVenda item : itens) {
			bd.remove(item.obterId());
		}
		
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