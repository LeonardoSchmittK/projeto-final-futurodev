package org.leoschmittk.service;

import org.leoschmittk.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Produto {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public Produto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public String getTotalValue(){
        ArrayList<org.leoschmittk.model.Produto> prodList = (ArrayList<org.leoschmittk.model.Produto>) produtoRepository.findAll();

        double totalValue = 0;
        for(int i = 0;i<=prodList.toArray().length-1;++i){
            if(prodList.get(i).getEstaComprado()==true){
                totalValue+=prodList.get(i).getValor();
            }

        }
        return "R$"+totalValue;
    }

    public String getProductsByPrice(double price){
        ArrayList<org.leoschmittk.model.Produto> prodList = (ArrayList<org.leoschmittk.model.Produto>) produtoRepository.findAll();

        double totalValue = 0;
        for(int i = 0;i<=prodList.toArray().length-1;++i){
            totalValue+=prodList.get(i).getValor();
        }
        return "R$"+totalValue;
    }
}
