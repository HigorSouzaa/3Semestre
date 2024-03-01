package classes;

public class Pedido {
    private int numMesa;
    private int numIdentificacaoPed;
    private double valor;
    private int quantidade;

    public Pedido(int numMesa, double valor,int numIdentificacaoPed, int quantidade) {
        this.numMesa = numMesa;
        this.valor = valor;
        this.quantidade = quantidade;
        this.numIdentificacaoPed = numIdentificacaoPed;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getNumCardapio() {
        return numIdentificacaoPed;
    }

    public void setNumCardapio(int numIdentificacaoPed) {
        this.numIdentificacaoPed = numIdentificacaoPed;
    }

        public String mostrarDados() {
        return "*****************************\n" +
                "\nNumero do pedido: " + numIdentificacaoPed +
                "\nValor do pedido: " + valor +
                "\nQuantidade: " + quantidade + "\n";

    }
}
