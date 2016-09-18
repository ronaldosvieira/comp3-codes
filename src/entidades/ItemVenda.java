package entidades;

public class ItemVenda {
	private Mobilia mobilia;
	private int quantidade;
	
	public Mobilia obterMobilia() {
		return this.mobilia;
	}
	
	public int obterQuantidade() {
		return this.quantidade;
	}
}
