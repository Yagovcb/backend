package br.com.bcbdigital.product_api.repository;

import br.com.bcbdigital.product_api.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select new Product(p.nome,	p.preco, p.productIdentifier,p.descricao, p.category)	"
            + "from	Product p	"
            + "join	Category	c	on	p.category.id	=	c.id	"
            + "where	c.id	=	:categoryId	")
    List<Product> getProductByCategory(
            long categoryId);

    Product findByProductIdentifier(String productIdentifier);

    Page<Product> findAll(Pageable pageable);
}
