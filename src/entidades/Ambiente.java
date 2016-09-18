package entidades;

import java.util.List;

public class Ambiente {
	private List<ItemVenda> itens;
	private int numParedes;
	private int numPortas;
	private float metragem;
	
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
}
