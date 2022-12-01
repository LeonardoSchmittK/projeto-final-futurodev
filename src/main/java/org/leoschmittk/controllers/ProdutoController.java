package org.leoschmittk.controllers;


import org.leoschmittk.model.Produto;
import org.leoschmittk.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {


    @Autowired
    org.leoschmittk.service.Produto produtoService;
    @Autowired
    private ProdutoRepository produtoRepository;

    // cadastrar produtos
    @PostMapping(value = "/", produces = "application/json" )
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto){
        Produto prod = produtoRepository.save(produto);
        return new ResponseEntity<Produto>(prod, HttpStatus.CREATED);
    }


    // resgatar produto por id
    @GetMapping(value="/{idProduto}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable(value="idProduto") Long idProduto){
        Produto pes = produtoRepository.findById(idProduto).get();
        return new ResponseEntity<Produto>(pes,HttpStatus.OK);
    }

    @DeleteMapping(value="/")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idProduto){
        produtoRepository.deleteById(idProduto);
        return new ResponseEntity<String>("Produto deletado com êxito!",HttpStatus.OK);
    }

    @PutMapping(value="/{id}",produces = "application/json")
    public Produto atualizar(@RequestBody Produto produto, @PathVariable Long id){
//        Produto prod = produtoRepository.save(produto);
//        return new ResponseEntity<Produto>(prod,HttpStatus.OK);

        return produtoRepository.findById(id)
                .map(address -> {
                    address.setNome(produto.getNome());
                    address.setEstaComprado(produto.getEstaComprado());
                    address.setValor(produto.getValor());

                    return produtoRepository.save(address);
                })
                .orElseGet(() -> {
                    return produtoRepository.save(produto);
                });
    }



    @RequestMapping(value = "/valorTotal", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getValorTotal() {
        return produtoService.getTotalValue();
    }



    @GetMapping(value="/buscarPorValor", produces = "application/json")
    public ResponseEntity<List<Produto>> getProdutoPorValor(@RequestParam(name="valor") double value){
        List<Produto> produtos=       produtoRepository.getProductsByPrice(value);
        return new ResponseEntity<List<Produto>>(produtos,HttpStatus.OK);
    }

    @GetMapping(value="/buscarTodosOsProdutos", produces = "application/json")
    public ResponseEntity<List<Produto>> getTodosOsProdutos(){
        List<Produto> produtos =       produtoRepository.getAllProducts();
        return new ResponseEntity<List<Produto>>(produtos,HttpStatus.OK);
    }
}
