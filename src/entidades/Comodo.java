package entidades;

import java.util.List;

public abstract class Comodo {
	private Integer id;
	protected List<Mobilia> mobiliasDisponiveis;
	private String descricao;
	private String tipo;
	
	public Comodo(String descricao, String tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
	public Comodo(int id, String descricao, String tipo) {
		this.id = id;
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
	abstract List<Mobilia> listaMobiliaDisponivel();
	
	public int obterId() {
		return this.id;
	}
	
	public String obterDescricao() {
		return this.descricao;
	}
	
	public String obterTipo() {
		return this.tipo;
	}
}
