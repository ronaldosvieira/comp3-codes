package entidades;

import java.util.List;

public class Contrato {
	private List<Ambiente> ambientes;
	private float comissao;
	
	public Contrato(float comissao) {
		this.comissao = comissao;
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
	

	public float obterComissao() {
		return this.comissao;
	}
}
