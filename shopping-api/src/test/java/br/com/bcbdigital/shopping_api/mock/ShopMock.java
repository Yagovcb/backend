package br.com.bcbdigital.shopping_api.mock;

import br.com.bcbdigital.backend.dtos.dto.ItemDTO;
import br.com.bcbdigital.backend.dtos.dto.ShopDTO;
import br.com.bcbdigital.backend.dtos.dto.ShopReportDTO;
import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.shopping_api.model.Item;
import br.com.bcbdigital.shopping_api.model.Shop;
import java.time.LocalDate;
import java.util.List;

public class ShopMock {

    public static Shop getShopMock() {
        Shop shop = new Shop();
        shop.setId(1L);
        shop.setDate(LocalDate.now());
        shop.setTotal(120.05F);
        shop.setItems(List.of(getItemMock()));
        shop.setUserIdentifier("yagovcb");
        return shop;
    }

    public static ShopDTO getShopDTOMock() {
        ShopDTO dto = new ShopDTO();
        dto.setDate(LocalDate.now());
        dto.setTotal(120.05F);
        dto.setItems(List.of(getItemDTOMock()));
        dto.setUserIdentifier("1");
        return dto;
    }


    public static Item getItemMock(){
        Item item = new Item();
        item.setPrice(100F);
        item.setProductIdentifier("Produto de teste");
        return item;
    }

    public static ItemDTO getItemDTOMock(){
        ItemDTO dto = new ItemDTO();
        dto.setPrice(100F);
        dto.setProductIdentifier("Produto de teste");
        return dto;
    }


    public static ShopReportDTO getShopReportDTOMock(){
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setCount(1);
        shopReportDTO.setMedia(100.0);
        shopReportDTO.setTotal(100.0);

        return shopReportDTO;
    }
}
