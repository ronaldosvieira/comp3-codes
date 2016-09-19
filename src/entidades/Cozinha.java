package entidades;

import java.util.List;

public class Cozinha extends Comodo {

	public Cozinha(String descricao) {
		super(descricao);
	}

	@Override
	List<Mobilia> listaMobiliaDisponivel() {
		return this.mobiliasDisponiveis;
	}

}
