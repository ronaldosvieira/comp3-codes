package entidades;

import java.util.List;

public class Cozinha extends Comodo {

	public Cozinha(String descricao) {
		super(descricao, "cozinha");
	}
	
	public Cozinha(String descricao, List<Mobilia> mobilias) {
		super(descricao, "cozinha", mobilias);
	}
	
	public Cozinha(int id, String descricao) {
		super(id, descricao, "cozinha");
	}
	
	public Cozinha(int id, String descricao, List<Mobilia> mobilias) {
		super(id, descricao, "cozinha", mobilias);
	}

	@Override
	public List<Mobilia> listaMobiliaDisponivel() {
		return this.mobiliasDisponiveis;
	}

}
