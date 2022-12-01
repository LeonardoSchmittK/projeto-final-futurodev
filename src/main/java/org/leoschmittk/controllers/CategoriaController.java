package org.leoschmittk.controllers;

import org.leoschmittk.model.Categoria;
import org.leoschmittk.model.Produto;
import org.leoschmittk.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;


    @PostMapping(value = "/", produces = "application/json" )
    public ResponseEntity<Categoria> cadastrar(@RequestBody Categoria categoria){
        Categoria cat = categoriaRepository.save(categoria);
        return new ResponseEntity<Categoria>(cat, HttpStatus.CREATED);
    }

    @GetMapping(value="/{idCategoria}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable(value="idCategoria") Long idCategoria){
        Categoria cat = categoriaRepository.findById(idCategoria).get();
        return new ResponseEntity<Categoria>(cat,HttpStatus.OK);
    }

    @DeleteMapping(value="/")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idCategoria){
        categoriaRepository.deleteById(idCategoria);
        return new ResponseEntity<String>("Categoria deletada com êxito!",HttpStatus.OK);
    }

    @GetMapping(value="/buscarTodasAsCategorias", produces = "application/json")
    public ResponseEntity<List<Categoria>> getTodasAsCategorias(){
        List<Categoria> categorias =       categoriaRepository.getAllCategories();
        return new ResponseEntity<List<Categoria>>(categorias,HttpStatus.OK);
    }

    @GetMapping(value="/{categoriaId}", produces = "application/json")
    public ResponseEntity<Categoria> getCategoriaPorId(@RequestParam(value = "categoriaId") Long id){
        Categoria categoria =       categoriaRepository.findById(id).get();
        return new ResponseEntity<Categoria>(categoria,HttpStatus.OK);
    }

    @GetMapping(value="/buscarCategoriaPorNome", produces = "application/json")
    public ResponseEntity<Categoria> getCategoriaPorNome(@RequestParam(name="nome") String nome){
        Categoria categoria = categoriaRepository.getCategoriaPorNome(nome);
        return new ResponseEntity<Categoria>(categoria,HttpStatus.OK);
    }


}
