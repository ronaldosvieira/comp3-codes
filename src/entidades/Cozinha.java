package entidades;

import java.util.List;

public class Cozinha extends Comodo {

	public Cozinha(String descricao, List<Mobilia> mobilias) {
		super(descricao, "cozinha", mobilias);
	}
	
	public Cozinha(int id, String descricao, List<Mobilia> mobilias) {
		super(id, descricao, "cozinha", mobilias);
	}

	@Override
	List<Mobilia> listaMobiliaDisponivel() {
		return this.mobiliasDisponiveis;
	}

}
