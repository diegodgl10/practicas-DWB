package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;

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
		return repo.findByCategoryId(category_id);
	}

	@Override
	public String createCategory(Category category) {
		Category categorySaved = repo.findByCategory(category.getCategory());
		if (categorySaved != null) {
			if (categorySaved.getStatus() == 0) {
				repo.activateCategory(categorySaved.getCategory_id());
			} else {
				return "category already exist";
			}
		}
		repo.createCategory(category.getCategory_id(), category.getCategory(), category.getAcronym());
		return "category created";
	}

	@Override
	public String updateCategory(Integer category_id, Category category) {
		Category categorySaved = repo.findByCategoryId(category_id);
		if (categorySaved == null) {
			return "category does not exist";
		}
		if (categorySaved.getStatus() == 0) {
			repo.activateCategory(categorySaved.getCategory_id());
			return "category is not active";
		}
		categorySaved = repo.findByCategory(category.getCategory());
		repo.updateCategory(category_id, category.getCategory(), category.getAcronym());
		return "category updated";
	}

	@Override
	public String deleteCategory(Integer category_id) {
		Category categorySaved = repo.findByCategoryId(category_id);
		if (categorySaved == null) {
			return "category does not exist";
		}
		repo.deleteById(category_id);
		return "category removed";
	}

}
