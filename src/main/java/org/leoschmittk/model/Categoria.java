package org.leoschmittk.model;

import javax.persistence.*;

@Entity
@Table
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120)
    private String nome;

    @Column(length = 120)
    private String descricao;

}
