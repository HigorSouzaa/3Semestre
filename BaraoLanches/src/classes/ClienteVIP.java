package classes;

import javax.swing.*;

public class ClienteVIP extends Cliente{
    private int nivelVIP;
    private double desconto;

    public ClienteVIP(String nome, String datNasc, String cpf, String telefone, String endereco, String email, int nivelVIP) {
        super(nome, datNasc, cpf, telefone, endereco, email);
        this.nivelVIP = nivelVIP;
    }

    public int getNivelVIP() {
        return nivelVIP;
    }

    public void setNivelVIP(int nivelVIP) {

        if (nivelVIP >= 0 && nivelVIP <= 10) {
            this.nivelVIP = nivelVIP;
        } else {
            JOptionPane.showMessageDialog(null, "Coloque um valor entre 0 e 10.", "ALLERT!!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double calcularDesconto(double nivelVIP) {
        if (nivelVIP == 0) {
            return desconto = 0;
        } else if (nivelVIP > 0 && nivelVIP <=3) {
            return desconto = 0.03;
        } else if (nivelVIP >= 4 && nivelVIP <=6) {
            return desconto = 0.06;
        } else if (nivelVIP >= 7 && nivelVIP <=10) {
            return desconto = 0.1;
        } else {
            JOptionPane.showMessageDialog(null, "Informe um valor valido", "Alert!!!", JOptionPane.WARNING_MESSAGE);
        }
        return desconto;
    }
}
