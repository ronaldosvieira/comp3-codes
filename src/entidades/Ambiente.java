package entidades;

import java.util.List;

public class Ambiente {
	private List<ItemVenda> itens;
	private int numParedes;
	private int numPortas;
	private float metragem;
	
	public Ambiente(int numParedes, int numPortas, float metragem) {
		this.numParedes = numParedes;
		this.numPortas = numPortas;
		this.metragem = metragem;
	}
	
	public float custo() {
		float custo = 0.0f;
		
		for (ItemVenda itemVenda : itens) {
			custo += itemVenda.obterMobilia().obterCusto() *
					itemVenda.obterQuantidade();
		}
		
		return custo;
	}
	
	public int tempoEntrega() {
		int tempo = 0;
		
		for (ItemVenda itemVenda : itens) {
			tempo += itemVenda.obterMobilia().obterCusto() *
					itemVenda.obterQuantidade();
		}
		
		return tempo;
	}

	public List<ItemVenda> obterItens() {
		return itens;
	}

	public int obterNumParedes() {
		return numParedes;
	}

	public int obterNumPortas() {
		return numPortas;
	}

	public float obterMetragem() {
		return metragem;
	}
	
	
}
