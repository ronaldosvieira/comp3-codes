package entidades;

public class ItemVenda {
	private Integer id;
	private Mobilia mobilia;
	private Ambiente ambiente;
	private int quantidade;
	
	public ItemVenda(int quantidade, Mobilia mobilia, Ambiente ambiente) {
		this.quantidade = quantidade;
		this.mobilia = mobilia;
		this.ambiente = ambiente;
	}
	
	public ItemVenda(int id, int quantidade, Mobilia mobilia, Ambiente ambiente) {
		this.id = id;
		this.quantidade = quantidade;
		this.mobilia = mobilia;
		this.ambiente = ambiente;
	}
	
	public int obterId() {
		return this.id;
	}
	
	public Mobilia obterMobilia() {
		return this.mobilia;
	}
	
	public Ambiente obterAmbiente() {
		return this.ambiente;
	}
	
	public int obterQuantidade() {
		return this.quantidade;
	}
}
