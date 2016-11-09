package entidades;

public class ItemVenda {
	private Integer id;
	private Mobilia mobilia;
	private int ambienteId;
	private int quantidade;
	
	public ItemVenda(int quantidade, Mobilia mobilia, int ambienteId) {
		this.quantidade = quantidade;
		this.mobilia = mobilia;
		this.ambienteId = ambienteId;
	}
	
	public ItemVenda(int id, int quantidade, Mobilia mobilia, int ambienteId) {
		this.id = id;
		this.quantidade = quantidade;
		this.mobilia = mobilia;
		this.ambienteId = ambienteId;
	}
	
	public int obterId() {
		return this.id;
	}
	
	public Mobilia obterMobilia() {
		return this.mobilia;
	}
	
	public int obterAmbienteId() {
		return this.ambienteId;
	}
	
	public int obterQuantidade() {
		return this.quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		ItemVenda other = (ItemVenda) obj;
		if (id == null) return false;
		if (!id.equals(other.id)) return false;
		
		return true;
	}
}
