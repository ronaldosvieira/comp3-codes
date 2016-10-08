package entidades;

import java.util.List;

public abstract class Comodo {
	private Integer id;
	protected List<Mobilia> mobiliasDisponiveis;
	private String descricao;
	
	public Comodo(String descricao) {
		this.descricao = descricao;
	}
	
	public Comodo(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	abstract List<Mobilia> listaMobiliaDisponivel();
	
	public int obterId() {
		return this.id;
	}
	
	public String obterDescricao() {
		return this.descricao;
	}
}
