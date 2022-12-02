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

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "categoria_nome", referencedColumnName = "nome",nullable = false)
    private Categoria categoria;

    @Column
    private Long categoria_id;

    @Column(length = 120,insertable = false,updatable = false)
    private String categoria_nome;

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


    public String getCategoria_nome() {
        return categoria_nome;
    }

    public void setCategoria_nome(String categoria_nome) {
        this.categoria_nome = categoria_nome;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Categoria getCategoria() {
//        return categoria;
//    }
//
//    public void setCategoria(Categoria categoria) {
//        this.categoria = categoria;
//    }


    public Long getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }

    public boolean isEstaComprado() {
        return estaComprado;
    }
}
