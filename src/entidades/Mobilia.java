package entidades;

public class Mobilia {
	private String descricao;
	private float custo;
	private int tempoEntrega;
	
	public float obterCusto() {
		return this.custo;
	}
	
	public int obterTempoEntrega() {
		return this.tempoEntrega;
	}
}
