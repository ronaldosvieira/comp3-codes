package entidades;

import java.util.List;

public class Sala extends Comodo {

	@Override
	List<Mobilia> listaMobiliaDisponivel() {
		return this.mobiliasDisponiveis;
	}

}
