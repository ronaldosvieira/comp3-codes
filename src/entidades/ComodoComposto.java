package entidades;

import java.util.ArrayList;
import java.util.List;

public class ComodoComposto extends Comodo {
	private List<Comodo> comodos;
	
	public ComodoComposto(String descricao) {
		super(descricao);
	}
	
	public ComodoComposto(int id, String descricao) {
		super(id, descricao);
	}
	
	@Override
	List<Mobilia> listaMobiliaDisponivel() {
		List<Mobilia> mobilias = new ArrayList<>();
		
		for (Comodo comodo : comodos) {
			mobilias.addAll(comodo.listaMobiliaDisponivel());
		}
		
		return mobilias;
	}
	
	public List<Comodo> obterComodos() {
		return this.comodos;
	}

}
