package entidades;

import java.util.List;

public class Sala extends Comodo {

	public Sala(String descricao, List<Mobilia> mobilias) {
		super(descricao, "sala", mobilias);
	}
	
	public Sala(int id, String descricao, List<Mobilia> mobilias) {
		super(id, descricao, "sala", mobilias);
	}

	@Override
	public List<Mobilia> listaMobiliaDisponivel() {
		return this.mobiliasDisponiveis;
	}

}
