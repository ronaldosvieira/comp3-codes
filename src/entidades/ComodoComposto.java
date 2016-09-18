package entidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComodoComposto extends Comodo {
	private List<Comodo> comodos;
	
	@Override
	List<Mobilia> listaMobiliaDisponivel() {
		List<Mobilia> mobilias = new ArrayList<>();
		
		for (Comodo comodo : comodos) {
			mobilias.addAll(comodo.listaMobiliaDisponivel());
		}
		
		return mobilias;
	}

}
