package com.daanielowsky.crm.Services;

import com.daanielowsky.crm.DTO.ItemDTO;
import com.daanielowsky.crm.Entities.Item;
import com.daanielowsky.crm.Repositories.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void registerItem(ItemDTO itemDTO) {
        Item item = new Item();
        ModelMapper modelMapper = new ModelMapper();

        item = modelMapper.map(itemDTO, Item.class);
        itemRepository.save(item);
    }

    public List<Item> getListOfItems() {
        return itemRepository.getAllBy();
    }
}
