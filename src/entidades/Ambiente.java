package entidades;

import java.util.List;

public class Ambiente {
	private Integer id;
	private List<ItemVenda> itens;
	private int numParedes;
	private int numPortas;
	private float metragem;
	
	public Ambiente(int numParedes, int numPortas, float metragem) {
		this.numParedes = numParedes;
		this.numPortas = numPortas;
		this.metragem = metragem;
	}
	
	public Ambiente(int id, int numParedes, int numPortas, float metragem) {
		this(numParedes, numPortas, metragem);
		this.id = id;
	}
	
	public float custo() {
		float custo = 0.0f;
		
		for (ItemVenda itemVenda : this.itens) {
			custo += itemVenda.obterMobilia().obterCusto() *
					itemVenda.obterQuantidade();
		}
		
		custo += 10 * this.numParedes;
		custo += 5 * this.numPortas;
		custo += 2.5 * this.metragem;
		
		return custo;
	}
	
	public int tempoEntrega() {
		int tempo = 0;
		
		for (ItemVenda itemVenda : this.itens) {
			if (itemVenda.obterMobilia().obterTempoEntrega() > tempo)
				tempo = itemVenda.obterMobilia().obterTempoEntrega();
		}
		
		tempo += 2 * this.numParedes;
		tempo += Math.ceil(this.numPortas / 2);
		
		return tempo;
	}
	
	public int obterId() {
		return this.id;
	}

	public List<ItemVenda> obterItens() {
		return this.itens;
	}

	public int obterNumParedes() {
		return this.numParedes;
	}

	public int obterNumPortas() {
		return this.numPortas;
	}

	public float obterMetragem() {
		return this.metragem;
	}
	
	
}
