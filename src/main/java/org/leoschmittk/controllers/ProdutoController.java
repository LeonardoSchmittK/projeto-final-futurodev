package org.leoschmittk.controllers;


import io.swagger.annotations.Api;
import org.leoschmittk.model.Categoria;
import org.leoschmittk.model.Produto;
import org.leoschmittk.repository.CategoriaRepository;
import org.leoschmittk.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(tags = "Produto")
@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {


    @Autowired
    org.leoschmittk.service.Produto produtoService;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // cadastrar produtos
    @PostMapping(value = "/", produces = "application/json" )
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto){
        Optional<Categoria> categoriaDeserved  = categoriaRepository.findById(produto.getCategoria_id());
        produto.setCategoria_nome(categoriaDeserved.get().getNome());
        Produto prodToSave = produto;

        Produto prod = produtoRepository.save(prodToSave);
                return new ResponseEntity<Produto>(prod, HttpStatus.CREATED);


    }


    // resgatar produto por id
    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<Produto> getProdutoById(@PathVariable(value="id")  Long id){
        Produto prod =       produtoRepository.findById(id).get();
        return new ResponseEntity<Produto>(prod,HttpStatus.OK);
    }

   @RequestMapping(value = "/{idProduto}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idProduto){
        produtoRepository.deleteById(idProduto);
        return new ResponseEntity<String>("Categoria deletada com êxito!",HttpStatus.OK);
    }

    @PutMapping(value="/{id}",produces = "application/json")
    public Produto atualizar(@RequestBody Produto produto, @PathVariable Long id){
        return produtoRepository.findById(id)
                .map(prod -> {
                    prod.setNome(produto.getNome());
                    prod.setEstaComprado(produto.getEstaComprado());
                    prod.setValor(produto.getValor());
                    prod.setCategoria_id(produto.getId());

                    return produtoRepository.save(prod);
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
