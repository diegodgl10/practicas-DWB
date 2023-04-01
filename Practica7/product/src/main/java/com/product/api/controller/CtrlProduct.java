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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.ApiResponse;
import com.product.api.dto.DtoCategoryId;
import com.product.api.entity.Product;
import com.product.api.service.SvcProduct;
import com.product.exception.ApiException;

@RestController
@RequestMapping("/product")
public class CtrlProduct {

	@Autowired
	SvcProduct svc;

	/**
	 * Regersa una lista con los productos con el mismo ID de la categoria que recibe
	 * como parametro.
	 * @return una lista con los productos con el mismo ID de la categoria que recibe
	 * como parametro.
	 */
	@GetMapping("/category/{category_id}")
	public ResponseEntity<List<Product>> listProducts(@PathVariable Integer category_id) {
		return new ResponseEntity<List<Product>>(svc.getProducts(category_id), HttpStatus.OK);
	}
	
	/**
	 * Regresa un endpoint del producto con el mismo gtin que recibe como parametro.
	 * @return un endpoint del producto con el mismo gtin que recibe como parametro.
	 */
	@GetMapping("/{gtin}")
	public ResponseEntity<Product> getProduct(@PathVariable String gtin) {
		return new ResponseEntity<Product>(svc.getProduct(gtin), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody Product in,
			BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST,
					bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.createProduct(in),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Integer id,
			@Valid @RequestBody Product in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST,
					bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateProduct(in, id),HttpStatus.OK);
	}

	/**
	 * Actualiza el stock de un producto
	 * @param gtin el gtin del producto al que se actualizara el stock
	 * @param stock la cantidad que se le rentara al stock actual del producto
	 * @return un response entity
	 */
	@PutMapping("/{gtin}/stock/{stock}")
	public ResponseEntity<ApiResponse> updateProductStock(@PathVariable("gtin") String gtin,
			@PathVariable("stock") Integer stock){
		return new ResponseEntity<>(svc.updateProductStock(gtin, stock),HttpStatus.OK);
	}

	/**
	 * Actualiza el id de un producto
	 * @param gtin el gtin del producto al que se actualizara el id de la categoria
	 * @param category_id el nuevo id de la categoria del producto
	 * @return un response entity
	 */
	@PutMapping("/{gtin}/category")
	public ResponseEntity<ApiResponse> updateProductCategory(@PathVariable("gtin") String gtin, 
			@Valid @RequestBody DtoCategoryId category_id, BindingResult bindingResult){
		return new ResponseEntity<>(svc.updateProductCategory(gtin, category_id.getCategory_id()),HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Integer id){
		return new ResponseEntity<>(svc.deleteProduct(id), HttpStatus.OK);
	}
}