package entidades;

import java.util.List;

public class Cozinha extends Comodo {

	public Cozinha(String descricao) {
		super(descricao);
	}
	
	public Cozinha(int id, String descricao) {
		super(id, descricao);
	}

	@Override
	List<Mobilia> listaMobiliaDisponivel() {
		return this.mobiliasDisponiveis;
	}

}
