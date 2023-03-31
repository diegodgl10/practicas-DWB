package com.product.api.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
import com.product.api.entity.Product;
import com.product.api.repository.RepoCategory;
import com.product.api.repository.RepoProduct;
import com.product.exception.ApiException;

@Service
public class SvcProductImp implements SvcProduct {

	@Autowired
	RepoProduct repo;
	
	@Autowired
	RepoCategory repoCategory;

	@Override
	public Product getProduct(String gtin) {
		Product product = repo.findByProductGtin(gtin);
		if (product != null) {
			product.setCategory(repoCategory.findByCategoryId(product.getCategory_id()));
			return product;
		}else
			throw new ApiException(HttpStatus.NOT_FOUND, "product does not exist");
	}

	@Override
	public List<Product> getProducts(Integer category_id) {
		return repo.findByCategoryId(category_id);
	}

	@Override
	public ApiResponse createProduct(Product in) {
		Category catSaved = repoCategory.findByCategoryId(in.getCategory_id());
		if (catSaved == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "category not found");
		}
		Product prodGtin = repo.findByProductGtin(in.getGtin());
		Product prodName = repo.findByProductName(in.getGtin());
		if (prodGtin != null) {
			if (prodGtin.getStatus() == 0) {
				repo.activateProduct(prodGtin.getProduct_id());
				prodGtin = repo.findByProductGtin(in.getGtin());
				repo.updateProduct(in.getProduct_id(), in.getGtin(), in.getProduct(), 
					in.getDescription(), in.getPrice(), in.getStock(), in.getCategory_id());
				return new ApiResponse("product activated");
			}
			throw new ApiException(HttpStatus.BAD_REQUEST, "product gtin already exist");
		}
		if (prodName != null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "product name already exist");
		}
		repo.createProduct(in.getProduct_id(), in.getGtin(), in.getProduct(), 
			in.getDescription(), in.getPrice(), in.getStock(), in.getCategory_id());
		return new ApiResponse("product created");
	}

	@Override
	public ApiResponse updateProduct(Product in, Integer id) {
		Integer updated = 0;
		try {
			updated = repo.updateProduct(id, in.getGtin(), in.getProduct(), in.getDescription(), in.getPrice(), in.getStock(), in.getCategory_id());
		}catch (DataIntegrityViolationException e) {
			if (e.getLocalizedMessage().contains("gtin"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "product gtin already exist");
			if (e.getLocalizedMessage().contains("product"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "product name already exist");
			if (e.contains(SQLIntegrityConstraintViolationException.class))
				throw new ApiException(HttpStatus.BAD_REQUEST, "category not found");
		}
		if(updated == 0)
			throw new ApiException(HttpStatus.BAD_REQUEST, "product cannot be updated");
		else
			return new ApiResponse("product updated");
	}

	@Override
	public ApiResponse updateProductStock(String gtin, Integer stock) {
		Product product = getProduct(gtin);
		if(stock > product.getStock())
			throw new ApiException(HttpStatus.BAD_REQUEST, "stock to update is invalid");
		
		repo.updateProductStock(gtin, product.getStock() - stock);
		return new ApiResponse("product stock updated");
	}

	@Override
	public ApiResponse deleteProduct(Integer id) {
		if (repo.deleteProduct(id) > 0)
			return new ApiResponse("product removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "product cannot be deleted");
	}

	public ApiResponse updateProductCategory(String gtin, Integer category_id) {
		Product product = getProduct(gtin);
		if (product == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "product does not exist");
		}
		Category catSaved = repoCategory.findByCategoryId(category_id);
		if (catSaved == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "category not found");
		}
		repo.updateProductCategory(gtin, category_id);
		return new ApiResponse("product category updated");
	}
}