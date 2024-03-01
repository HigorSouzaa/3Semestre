package classes;

public class Mesa{
	private int numMesa;
	private int numCardapio;
	private int quantidade;
	private double valorTotal;
	private String nomeCardapio;
	
	public Mesa(int numMesa, int numCardapio, int quantidade, double valorTotal, String nomeCardapio) {
		super();
		this.numMesa = numMesa;
		this.numCardapio = numCardapio;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
		this.nomeCardapio = nomeCardapio;
	}



	public String mostrarDados() {
		return this.numCardapio + "\t" + this.nomeCardapio + "\t" + this.quantidade + "\tR$" + this.valorTotal + "\n";
	}



	public double getValorTotal() {
		return valorTotal;
	}



	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	
	
	



}
