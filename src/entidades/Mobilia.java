package entidades;

public class Mobilia {
	private Integer id;
	private String descricao;
	private float custo;
	private int tempoEntrega;
	
	public Mobilia(String descricao, float custo, int tempoEntrega) {
		this.descricao = descricao;
		this.custo = custo;
		this.tempoEntrega = tempoEntrega;
	}
	
	public Mobilia(int id, String descricao, float custo, int tempoEntrega) {
		this.id = new Integer(id);
		this.descricao = descricao;
		this.custo = custo;
		this.tempoEntrega = tempoEntrega;
	}
	
	public int obterId() {
		return id;
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
