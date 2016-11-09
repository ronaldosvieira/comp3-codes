package entidades;

public class ItemVenda {
	private Integer id;
	private Mobilia mobilia;
	private int ambienteId;
	private int quantidade;
	
	public ItemVenda(int quantidade, Mobilia mobilia, int ambienteId) {
		this.quantidade = quantidade;
		this.mobilia = mobilia;
		this.ambienteId = ambienteId;
	}
	
	public ItemVenda(int id, int quantidade, Mobilia mobilia, int ambienteId) {
		this.id = id;
		this.quantidade = quantidade;
		this.mobilia = mobilia;
		this.ambienteId = ambienteId;
	}
	
	public int obterId() {
		return this.id;
	}
	
	public Mobilia obterMobilia() {
		return this.mobilia;
	}
	
	public int obterAmbienteId() {
		return this.ambienteId;
	}
	
	public int obterQuantidade() {
		return this.quantidade;
	}
}
