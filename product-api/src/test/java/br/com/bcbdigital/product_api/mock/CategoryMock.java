package br.com.bcbdigital.product_api.mock;

import br.com.bcbdigital.backend.dtos.dto.CategoryDTO;
import br.com.bcbdigital.product_api.model.Category;

public class CategoryMock {

    public static Category getCategoryMock(){
        Category category = new Category();
        category.setId(1);
        category.setNome("Categoria de teste");
        return category;
    }

    public static CategoryDTO getCategoryDTOMock(){
        CategoryDTO category = new CategoryDTO();
        category.setNome("Categoria de teste");
        return category;
    }
}
