package entidades;

import java.util.List;

public class Quarto extends Comodo {

	public Quarto(String descricao) {
		super(descricao);
	}
	
	public Quarto(int id, String descricao) {
		super(id, descricao);
	}

	@Override
	List<Mobilia> listaMobiliaDisponivel() {
		return this.mobiliasDisponiveis;
	}

}
