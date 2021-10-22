package br.com.bcbdigital.shopping_api.service;

import br.com.bcbdigital.backend.dtos.dto.*;
import br.com.bcbdigital.shopping_api.repository.ReportRepository;
import br.com.bcbdigital.shopping_api.repository.ShopRepository;
import br.com.bcbdigital.shopping_api.model.Shop;
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
 *  {@link Service} controller para gerenciar as  ações do controller {@link br.com.bcbdigital.shopping_api.controller.ShopController}.
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {

    @Autowired
    private ShopRepository repository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Método responsavel por retornar todas as compras
     *
     * @return um {@link List<ShopDTO>} com todos os registros da entidade {@link ShopDTO} criados
     * */
    public List<ShopDTO> getAll() {
        List<Shop> shops = repository.findAll();
        return shops.stream().map(shop -> modelMapper.map(shop, ShopDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Método responsavel por retornar todas as compras com base no usuario que comprou
     *
     * @return um {@link List<ShopDTO>} com todos os registros da entidade {@link ShopDTO} criados
     * */
    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> shops = repository.findAllByUserIdentifier(userIdentifier);
        return shops.stream().map(shop -> modelMapper.map(shop, ShopDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Método responsavel por retornar todas as compras ordenado pelas datas das compras
     *
     * @return um {@link List<ShopDTO>} com todos os registros da entidade {@link ShopDTO} criados
     * */
    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        List<Shop> shops = repository.findAllByDateGreaterThan(shopDTO.getDate());
        return shops.stream().map(shop -> modelMapper.map(shop, ShopDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Método responsavel por retornar um {@link ShopDTO} especifico, dado seu id
     *
     * @param productId do {@link UserDTO} que está se buscando
     *
     * @return um {@link ShopDTO} especifico
     * */
    public ShopDTO findById(long productId) {
        Optional<Shop> shop = repository.findById(productId);
        return shop.map(shopp -> modelMapper.map(shopp, ShopDTO.class)).orElse(null);
    }

    /**
     * Método responsavel por salvar um {@link Shop} especifico, dado seu {@link ShopDTO}
     *
     * @param shopDTO entidade {@link ShopDTO} que será persistida
     * @param key do {@link UserDTO}
     *
     * @return um {@link ShopDTO} já persistido no banco
     * */
    public ShopDTO save(ShopDTO shopDTO, String key) {
        if (Objects.isNull(userService.getUserByCpf( shopDTO.getUserIdentifier(), key ) ) ) {
            return null;
        }
        if (!validateProducts(shopDTO.getItems())) {
            return null;
        }
        shopDTO.setTotal(shopDTO.getItems().stream().map(ItemDTO::getPrice).reduce((float) 0, Float::sum));

        Shop shop = modelMapper.map(shopDTO, Shop.class);
        shop.setDate(LocalDate.now());
        shop = repository.save(shop);
        return modelMapper.map(shop, ShopDTO.class);
    }

    /**
     * Método responsavel por retornar uma {@link List<ShopDTO>} com base no parametros que serão utilizados no filtro
     *
     * @param dataInicio  representa o atributo dataInicio da entidade {@link ShopDTO}, podendo ser null
     * @param dataFim representa o maior valor do atributo dataFim da entidade {@link ShopDTO}, podendo ser null
     * @param valorMinimo  representa o valor do atributo valorMinimo da entidade {@link ShopDTO}, podendo ser null
     *
     * @return uma {@link List<ShopDTO>} todos os produtos que se encaixem no filtro
     * */
    public List<ShopDTO> getShopsByFilter(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo) {
        List<Shop> shops = reportRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
        return shops.stream().map(shop -> modelMapper.map(shop, ShopDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * {Método responsavel por retornar uma {@link List<ShopDTO>} com base no parametros que serão utilizados no filtro
     *
     * @param dataInicio  representa o atributo dataInicio da entidade {@link ShopDTO}, podendo ser null
     * @param dataFim representa o maior valor do atributo dataFim da entidade {@link ShopDTO}, podendo ser null
     *
     * @return uma {@link ShopReportDTO} todos os produtos que se encaixem no relatorio
     * */
    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim) {
        return reportRepository.getReportByDate(dataInicio, dataFim);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        items.forEach(itemDTO -> {
            ProductDTO productDTO = productService.getProductByIdentifier(itemDTO.getProductIdentifier());
            if (Objects.isNull(productDTO)) {
                return;
            }
            itemDTO.setPrice(productDTO.getPreco());
        });

        return true;
    }
}
