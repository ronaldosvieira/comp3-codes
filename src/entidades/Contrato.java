package entidades;

import java.util.List;

public class Contrato {
	private Integer id;
	private List<Ambiente> ambientes;
	private float comissao;
	
	public Contrato(float comissao) {
		this.comissao = comissao;
	}
	
	public Contrato(int id, float comissao) {
		this.id = id;
		this.comissao = comissao;
	}
	
	public void inserirAmbiente(Ambiente ambiente) {
		if (!this.ambientes.contains(ambiente)) {
			this.ambientes.add(ambiente);
		}
	}
	
	public float valorContrato() {
		float valor = 0.0f;
		
		for (Ambiente ambiente : ambientes) {
			valor += ambiente.custo();
		}
		
		valor *= 1 + comissao;
		
		return valor;
	}
	
	public int prazo() {
		int prazoMax = 0;
		
		for (Ambiente ambiente : ambientes) {
			if (ambiente.tempoEntrega() > prazoMax) {
				prazoMax = ambiente.tempoEntrega();
			}
		}
		
		return prazoMax;
	}
	
	public int obterId() {
		return this.id;
	}

	public float obterComissao() {
		return this.comissao;
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
		
		Contrato other = (Contrato) obj;
		if (id == null) return false;
		if (!id.equals(other.id)) return false;
		
		return true;
	}

	
	public List<Ambiente> obterAmbientes() {
		return this.ambientes;
	}
}
