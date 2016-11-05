package entidades;

import java.util.ArrayList;
import java.util.List;

public class ComodoComposto extends Comodo {
	private List<Comodo> comodos;
	
	public ComodoComposto(String descricao) {
		super(descricao, "comodo_composto");
	}
	
	public ComodoComposto(String descricao, List<Mobilia> mobilias) {
		super(descricao, "comodo_composto", mobilias);
	}
	
	public ComodoComposto(int id, String descricao) {
		super(id, descricao, "comodo_composto");
	}
	
	public ComodoComposto(int id, String descricao, List<Mobilia> mobilias) {
		super(id, descricao, "comodo_composto", mobilias);
	}
	
	@Override
	public List<Mobilia> listaMobiliaDisponivel() {
		List<Mobilia> mobilias = new ArrayList<>();
		
		for (Comodo comodo : comodos) {
			mobilias.addAll(comodo.listaMobiliaDisponivel());
		}
		
		return mobilias;
	}
	
	public List<Comodo> obterComodos() {
		return this.comodos;
	}

	public void alterarComodos(List<Comodo> comodos) {
		this.comodos = comodos;
	}
}
