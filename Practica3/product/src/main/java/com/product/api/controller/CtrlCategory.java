package com.product.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Category;

@RestController
/**
 * Controlador de cateogiras.
 * @author Diego Arturo Zamora Cruz.
 */
public class CtrlCategory {

    /* Lista de categorias. */
    // private List<Category> lsCategory = new ArrayList<Category>();
    private List<Category> lsCategory = Arrays.asList(new Category[] {
    new Category (1, "Linea Blanca", "LB"),
    new Category (2, "Abarrotes", "Abar"),
    new Category (3, "Electrónica", "Electr")
    });

    /**
	 * Regresa un endpoint de todas las categorias.
	 * @return un endpoint de todas las categorias.
	 */
	@GetMapping("/ListCategories")
	public ResponseEntity<List<Category>> listCategories() {
		return new ResponseEntity<List<Category>>(this.lsCategory, HttpStatus.OK);
	}

    /**
	 * Regresa un endpoint de la categoria con el mismo ID que recibe como parametro.
	 * @return un endpoint de la categoria con el mismo ID que recibe como parametro.
	 */
	@GetMapping("/ReadCategories/{category_id}")
	public ResponseEntity<Category> readCategory(@PathVariable int category_id) {
        Category tmp = null;
        for (int i = 0; i < this.lsCategory.size(); i++) {
            if (this.lsCategory.get(i).getCategory_id() == category_id) {
                tmp = this.lsCategory.get(i);
            }
        }
		return new ResponseEntity<Category>(tmp, HttpStatus.OK);
	}

    /**
	 * Agrega la categoria recibida si no existe una con el mismo ID.
	 * Agrega la categoria recibida si no existe una con el mismo ID.
	 */
	@PostMapping("/CreateCategories")
	public ResponseEntity<String> createCategory(@RequestBody Category cat) {
        String msj = "";
        HttpStatus status;
        for (int i = 0; i < this.lsCategory.size(); i++) {
            if (this.lsCategory.get(i).getCategory_id() == cat.getCategory_id()) {
                msj = "category already exist";
                status = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<String>(msj, status);
            }
        }
        // this.lsCategory.add(cat);
        msj = "category created";
        status = HttpStatus.OK;
        return new ResponseEntity<String>(msj, status);
	}

    /**
	 * Actualiza una categoria con los atributos recibidos.
	 * Actualiza una categoria con los atributos recibidos.
	 */
	@PutMapping("/UpdateCategories/{category_id}")
	public ResponseEntity<String> updateCategory(@PathVariable int category_id,
    @RequestBody Category cat) {
        String msj ="category does not exist";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        for (int i = 0; i < this.lsCategory.size(); i++) {
            if (this.lsCategory.get(i).getCategory_id() == cat.getCategory_id()) {
                this.lsCategory.set(i, cat);
                msj = "category updated";
                status = HttpStatus.OK;
            }
        }
        return new ResponseEntity<String>(msj, status);
	}

    /**
	 * Elimita una categoria con el ID recibido.
	 * Elimita una categoria con el ID recibido.
	 */
	@DeleteMapping("/DeleteCategories/{category_id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int category_id) {
        String msj = "category does not exist";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        int remove = -1;
        for (int i = 0; i < this.lsCategory.size(); i++) {
            Category cat = this.lsCategory.get(i);
            if (cat.getCategory_id() == category_id) {
                remove = i;
            }
        }
        if (remove != -1) {
            // this.lsCategory.remove(remove);
            msj = "category removed";
            status = HttpStatus.OK;        	
        }
        return new ResponseEntity<String>(msj, status);
	}
}
