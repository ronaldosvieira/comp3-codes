package entidades;

import java.util.ArrayList;
import java.util.List;

public class Ambiente {
	private Integer id;
	private List<ItemVenda> itens;
	private int contratoId;
	private int numParedes;
	private int numPortas;
	private float metragem;
	
	public Ambiente(int numParedes, int numPortas, float metragem, int contratoId) {
		this.numParedes = numParedes;
		this.numPortas = numPortas;
		this.metragem = metragem;
		this.contratoId = contratoId;
		this.itens = new ArrayList<>();
	}
	
	public Ambiente(int id, int numParedes, int numPortas, float metragem, int contratoId) {
		this(numParedes, numPortas, metragem, contratoId);
		this.id = id;
	}
	
	public void inserirItemVenda(ItemVenda itemVenda) {
		if (!itens.contains(itemVenda)) {
			itens.add(itemVenda);
		}
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
	
	public int obterContratoId() {
		return this.contratoId;
	}
	
}
