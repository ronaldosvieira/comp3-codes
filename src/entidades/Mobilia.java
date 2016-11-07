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
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + tempoEntrega;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Mobilia other = (Mobilia) obj;
		if (id == null) return false;
		if (!id.equals(other.id)) return false;

		return true;
	}
}
