package model;

import java.util.Comparator;

/**
 * @author ridneto
 */
public class Customer{
    private int id;
    private String cpf_cnpj, nome;
    private boolean active;
    private double valor_total;

    public Customer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }
    
    
    
    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public String getNome() {
        return nome;
    }

    public boolean isActive() {
        return active;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }     
}