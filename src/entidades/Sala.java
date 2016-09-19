package entidades;

import java.util.List;

public class Sala extends Comodo {

	public Sala(String descricao) {
		super(descricao);
	}

	@Override
	List<Mobilia> listaMobiliaDisponivel() {
		return this.mobiliasDisponiveis;
	}

}
