package br.com.bcbdigital.product_api.service;

import br.com.bcbdigital.backend.dtos.dto.*;
import br.com.bcbdigital.backend.dtos.exceptions.CategoryNotFoundException;
import br.com.bcbdigital.backend.dtos.exceptions.ProductNotFoundException;
import br.com.bcbdigital.product_api.model.Category;
import br.com.bcbdigital.product_api.model.Product;
import br.com.bcbdigital.product_api.repository.CategoryRepository;
import br.com.bcbdigital.product_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  {@link Service} controller para gerenciar as  ações do controller {@link br.com.bcbdigital.product_api.controller.ProductController}.
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Método responsavel por retornar todas as compras
     *
     * @return um {@link List<ProductDTO>} com todos os registros da entidade {@link ProductDTO} criados
     * */
    public	List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return	products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Método responsavel por retornar uma {@link List<ProductDTO>} especifico, dado o id da {@link Category}
     *
     * @param categoryId do {@link CategoryDTO} que está se buscando
     *
     * @return uma {@link List<ProductDTO>} com base na {@link CategoryDTO}
     * */
    public	List<ProductDTO> getProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return	products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Método responsavel por retornar um {@link ProductDTO} especifico, dado seu id
     *
     * @param productIdentifier do {@link ProductDTO} que está se buscando
     *
     * @throws ProductNotFoundException caso o produto não seja encontrado
     *
     * @return um {@link ProductDTO} especifico
     * */
    public	ProductDTO findByProductIdentifier(String productIdentifier) {
        Product	product	= productRepository.findByProductIdentifier(productIdentifier);
        if (Objects.nonNull(product)){
            return modelMapper.map(product, ProductDTO.class);
        } else {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Método responsavel por salvar um {@link ProductDTO} especifico
     *
     * @param productDTO entidade {@link ProductDTO} que será persistida
     *
     * @return um {@link ProductDTO} já persistido no banco
     * */
    public	ProductDTO save(ProductDTO	productDTO) {
        boolean existsCategory = categoryRepository.existsById(productDTO.getCategory().getId());

        if (!existsCategory){
            throw new CategoryNotFoundException();
        }

        Product	product	= productRepository.save(modelMapper.map(productDTO, Product.class));
        return modelMapper.map(product, ProductDTO.class);
    }

    /**
     * Método responsavel por deletar um {@link ProductDTO} dado seu id
     *
     * @param productId do {@link ProductDTO} que será deletado
     *
     * @return o {@link DetalheRespostaDTO} informando que o produto foi deletado
     * */
    public DetalheRespostaDTO delete(long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            productRepository.delete(product.get());
            return new DetalheRespostaDTO("Produto deletado com sucesso", 200, LocalDate.now());
        } else {
            throw new ProductNotFoundException();
        }
    }
}
