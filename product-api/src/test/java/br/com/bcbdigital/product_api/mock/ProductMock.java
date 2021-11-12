package br.com.bcbdigital.product_api.mock;


import br.com.bcbdigital.backend.dtos.dto.ProductDTO;
import br.com.bcbdigital.product_api.model.Product;

public class ProductMock {

    public static Product getProductMock(){
        Product product = new Product();
        product.setId(1);
        product.setNome("Produto de teste");
        product.setProductIdentifier("identificador de produto de teste");
        product.setCategory(CategoryMock.getCategoryMock());
        product.setDescricao("Produto de descrição");
        product.setPreco(11.22f);
        return product;
    }

    public static ProductDTO getProductDTOMock(){
        ProductDTO product = new ProductDTO();
        product.setNome("Produto de teste");
        product.setProductIdentifier("identificador de produto de teste");
        product.setCategory(CategoryMock.getCategoryDTOMock());
        product.setPreco(11.22f);
        return product;
    }
}
