package org.leoschmittk.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120,nullable = false,unique = true)
    private String nome;



    @Column(nullable = false)
    private double valor;

    @Column()
    private boolean estaComprado;


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//         produto = () o;
//        return Objects.equals(id, produto.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean getEstaComprado() {
        return estaComprado;
    }

    public void setEstaComprado(boolean estaComprado) {
        this.estaComprado = estaComprado;
    }
}
