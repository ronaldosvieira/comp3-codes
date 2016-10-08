package entidades;

public class ItemVenda {
	private Integer id;
	private Mobilia mobilia;
	private int quantidade;
	
	public ItemVenda(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public ItemVenda(int id, int quantidade) {
		this.id = id;
		this.quantidade = quantidade;
	}
	
	public int obterId() {
		return this.id;
	}
	
	public Mobilia obterMobilia() {
		return this.mobilia;
	}
	
	public int obterQuantidade() {
		return this.quantidade;
	}
}
