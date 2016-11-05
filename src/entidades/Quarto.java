package entidades;

import java.util.List;

public class Quarto extends Comodo {

	public Quarto(String descricao) {
		super(descricao, "quarto");
	}
	
	public Quarto(String descricao, List<Mobilia> mobilias) {
		super(descricao, "quarto", mobilias);
	}
	
	public Quarto(int id, String descricao) {
		super(id, descricao, "quarto");
	}
	
	public Quarto(int id, String descricao, List<Mobilia> mobilias) {
		super(id, descricao, "quarto", mobilias);
	}

	@Override
	public List<Mobilia> listaMobiliaDisponivel() {
		return this.mobiliasDisponiveis;
	}

}
