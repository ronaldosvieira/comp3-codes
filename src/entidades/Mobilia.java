package entidades;

public class Mobilia {
	private String descricao;
	private float custo;
	private int tempoEntrega;
	
	public Mobilia(String descricao, float custo, int tempoEntrega) {
		this.descricao = descricao;
		this.custo = custo;
		this.tempoEntrega = tempoEntrega;
	}
	
	public String obterDescricao() {
		return this.descricao;
	}
	
	public float obterCusto() {
		return this.custo;
	}
	
	public int obterTempoEntrega() {
		return this.tempoEntrega;
	}
}
