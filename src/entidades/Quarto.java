package entidades;

import java.util.List;

public class Quarto extends Comodo {

	@Override
	List<Mobilia> listaMobiliaDisponivel() {
		return this.mobiliasDisponiveis;
	}

}
