package entidades;

import java.util.List;

public abstract class Comodo {
	protected List<Mobilia> mobiliasDisponiveis;
	private String descricao;
	
	public Comodo(String descricao) {
		this.descricao = descricao;
	}
	
	abstract List<Mobilia> listaMobiliaDisponivel();
	
	public String obterDescricao() {
		return this.descricao;
	}
}
