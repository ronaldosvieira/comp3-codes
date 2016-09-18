package entidades;

import java.util.List;

public class Cozinha extends Comodo {

	@Override
	List<Mobilia> listaMobiliaDisponivel() {
		return this.mobiliasDisponiveis;
	}

}
