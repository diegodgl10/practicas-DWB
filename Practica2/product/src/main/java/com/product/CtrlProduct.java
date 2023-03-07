package com.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * Controlador de productos.
 * @author Diego Arturo Zamora Cruz.
 */
public class CtrlProduct {
	
	/* Lista de categorias. */
	private Category[] lsCategory = {
			new Category (1, "Linea Blanca", "LB"),
			new Category (2, "Electrónica", "Electr"),
			new Category (3, "Casa y Jardin", "CyJ"),
			new Category (4, "Mascotas", "Masc")};
	
	/**
	 * Regresa un endpoint de las categorias.
	 * @return un endpoint de las categorias.
	 */
	@GetMapping("/category")
	public Category[] holaProduct() {
		return lsCategory;
	}

}
