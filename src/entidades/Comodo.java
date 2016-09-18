package entidades;

import java.util.List;

public abstract class Comodo {
	protected List<Mobilia> mobiliasDisponiveis;
	private String descricao;
	
	abstract List<Mobilia> listaMobiliaDisponivel();
}
