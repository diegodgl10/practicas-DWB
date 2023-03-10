package com.product.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;

/**
 * Controlador de cateogiras.
 * @author Diego Arturo Zamora Cruz.
 */
@RestController
//@RequestMapping("/category")
public class CtrlCategory {
	
	@Autowired
	SvcCategory svc;

    /**
	 * Regresa un endpoint de todas las categorias.
	 * @return un endpoint de todas las categorias.
	 */
	@GetMapping("/category")
	public ResponseEntity<List<Category>> listCategories() {
		return new ResponseEntity<List<Category>>(svc.getCategories(), HttpStatus.OK);
	}

    /**
	 * Regresa un endpoint de la categoria con el mismo ID que recibe como parametro.
	 * @return un endpoint de la categoria con el mismo ID que recibe como parametro.
	 */
	@GetMapping("/category/{category_id}")
	public ResponseEntity<Category> readCategory(@PathVariable int category_id) {
		return new ResponseEntity<Category>(svc.getCategory(category_id), HttpStatus.OK);
	}

    /**
	 * Agrega la categoria recibida si no existe una con el mismo ID.
	 * Agrega la categoria recibida si no existe una con el mismo ID.
	 */
	@PostMapping("/category")
	public ResponseEntity<String> createCategory(@Valid @RequestBody Category cat, BindingResult bindingResult) {
        String msj = "";
        if (bindingResult.hasErrors()) {
			msj = bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<String>(msj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(svc.createCategory(cat), HttpStatus.OK);
	}

    /**
	 * Actualiza una categoria con los atributos recibidos.
	 * Actualiza una categoria con los atributos recibidos.
	 */
	@PutMapping("/category/{category_id}")
	public ResponseEntity<String> updateCategory(@PathVariable int category_id,
    @Valid @RequestBody Category cat, BindingResult bindingResult) {
		String msj = "";
        if (bindingResult.hasErrors()) {
        	msj = bindingResult.getAllErrors().get(0).getDefaultMessage();
        	return new ResponseEntity<String>(msj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(svc.updateCategory(category_id, cat), HttpStatus.OK);
	}

    /**
	 * Elimita una categoria con el ID recibido.
	 * Elimita una categoria con el ID recibido.
	 */
	@DeleteMapping("/category/{category_id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int category_id) {
        return new ResponseEntity<String>(svc.deleteCategory(category_id), HttpStatus.OK);
	}
}
