package entidades;

import java.util.List;

public class Contrato {
	private List<Ambiente> ambientes;
	private float comissao;
	
	public float valorContrato() {
		float valor = 0.0f;
		
		for (Ambiente ambiente : ambientes) {
			valor += ambiente.custo();
		}
		
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
}
