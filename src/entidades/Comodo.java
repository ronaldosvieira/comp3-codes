package entidades;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public abstract class Comodo {
	private Integer id;
	protected List<Mobilia> mobiliasDisponiveis;
	private String descricao;
	private String tipo;
	
	public Comodo(String descricao, String tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
	public Comodo(String descricao, String tipo, List<Mobilia> mobilias) {
		this(descricao, tipo);
		this.mobiliasDisponiveis = mobilias;
	}
	
	public Comodo(int id, String descricao, String tipo, List<Mobilia> mobilias) {
		this(descricao, tipo, mobilias);
		this.id = id;
	}
	
	public Comodo(int id, String descricao, String tipo) {
		this(id, descricao, tipo, new ArrayList<>());
	}
	
	public static Comodo create(int id, String descricao, String tipo, List<Mobilia> mobilias) {
		switch (tipo) {
		case "cozinha":
			return new Cozinha(id, descricao, mobilias);
			
		case "sala":
			return new Sala(id, descricao, mobilias);
			
		case "quarto":
			return new Quarto(id, descricao, mobilias);
			
		case "comodo_composto":
			return new ComodoComposto(id, descricao, mobilias);
			
		default:
			throw new InvalidParameterException("Tipo de cômodo especificado (" + 
				tipo + ") não existe.");
		}
	}
	
	abstract public List<Mobilia> listaMobiliaDisponivel();
	
	public int obterId() {
		return this.id;
	}
	
	public String obterDescricao() {
		return this.descricao;
	}
	
	public String obterTipo() {
		return this.tipo;
	}
}
