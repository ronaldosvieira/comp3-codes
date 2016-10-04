package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.ItemVenda;

public class ItemVendaBanco implements AutoCloseable {
	Connection conn;
	
	public ItemVendaBanco() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		
		String url = "jdbc:h2:~/decoradora";
		String user = "decoradora";
		String pass = "123456";
		
		conn = DriverManager.getConnection(url, user, pass);
	}
	
	public List<ItemVenda> get() throws SQLException {
		String sql = "select * from item_venda";
		ResultSet rs = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		
		List<ItemVenda> results = new ArrayList<>();
		
		while (rs.next()) {
			results.add(new ItemVenda(
					rs.getInt("id"),
					rs.getInt("quantidade")));
		}
		
		return results;
	}
	
	public ItemVenda get(int id) throws SQLException, IndexOutOfBoundsException {
		String sql = "select * "
				+ "from item_venda "
				+ "where id = ?";
		ResultSet rs = null;
		ItemVenda ItemVenda = null;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		if (stmt.execute()) rs = stmt.getResultSet();
		else {
			throw new IndexOutOfBoundsException();
		}
		
		if (rs.next()) {
			ItemVenda = new ItemVenda(
					rs.getInt("id"),
					rs.getInt("quantidade"));
		} else {
			throw new IndexOutOfBoundsException();
		}
		
		return ItemVenda;
	}
	
	public int insert(ItemVenda ItemVenda) throws SQLException {
		String sql = "insert into item_venda (quantidade) "
				+ "values (?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, ItemVenda.obterQuantidade());
		
		stmt.executeUpdate();
		
		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				
				return id;
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao inserir item venda.");
		}
		
		return -1;
	}

	public void update(int id, ItemVenda ItemVenda) throws SQLException {
		String sql = "update item_venda set "
				+ "quantidade = ? "
				+ "where id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, ItemVenda.obterQuantidade());
		stmt.setInt(2, id);
		
		stmt.executeUpdate();
	}
	
	public boolean remove(int id) throws SQLException {
		String sql = "delete from item_venda where id = ?";
		
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