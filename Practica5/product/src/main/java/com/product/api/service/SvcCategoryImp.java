package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;

@Service
public class SvcCategoryImp implements SvcCategory {
	
	@Autowired
	RepoCategory repo;

	@Override
	public List<Category> getCategories() {
		return repo.findByStatus(1);
	}

	@Override
	public Category getCategory(Integer category_id) {
		Category category = repo.findByCategoryId(category_id);
		String msj = "cateogry does not exist";
		if (category == null) {
			throw new ApiException(HttpStatus.NOT_FOUND, msj);
		}
		return category;
	}

	@Override
	public ApiResponse createCategory(Category category) {
		Category categorySaved = repo.findByCategory(category.getCategory());
		if (categorySaved != null) {
			if (categorySaved.getStatus() == 0) {
				repo.activateCategory(categorySaved.getCategory_id());
				return new ApiResponse("category has been activated");
			}
			throw new ApiException(HttpStatus.BAD_REQUEST, "category already exist");
		}
		repo.createCategory(category.getCategory_id(), category.getCategory(),
			category.getAcronym());
		return new ApiResponse("category created");
	}

	@Override
	public ApiResponse updateCategory(Integer category_id, Category category) {
		Category categorySaved = repo.findByCategoryId(category_id);
		if (categorySaved == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "category does not exist");
		}
		if (categorySaved.getStatus() == 0) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "category is not active");
		}
		categorySaved = repo.findByCategory(category.getCategory());
		repo.updateCategory(category_id, category.getCategory(), category.getAcronym());
		return new ApiResponse("category updated");
	}

	@Override
	public ApiResponse deleteCategory(Integer category_id) {
		Category categorySaved = repo.findByCategoryId(category_id);
		if (categorySaved == null) {
			throw new ApiException(HttpStatus.NOT_FOUND, "category does not exist");
		}
		repo.deleteById(category_id);
		return new ApiResponse("category removed");
	}

}
