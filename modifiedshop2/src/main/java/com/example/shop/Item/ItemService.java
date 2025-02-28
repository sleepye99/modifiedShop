package com.example.shop.Item;

import com.example.shop.Category.CategoryEntity;
import com.example.shop.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements ItemUseCase {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemMapper itemMapper;

    public Item addItem(ItemDTO itemDTO) {
        // Create domain model from DTO
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setStockQuantity(itemDTO.getStockQuantity());
        item.setCategoryId(itemDTO.getCategoryId());

        // Map to entity
        ItemEntity itemEntity = itemMapper.DomainModelToEntityForItem(item);

        // Handle category association
        if (itemDTO.getCategoryId() != null) {
            CategoryEntity categoryEntity = categoryRepository.findById(itemDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            // Sync both sides of the relationship
            itemEntity.setCategoryEntity(categoryEntity);       // Set the owning side
            categoryEntity.getItems().add(itemEntity);          // Sync the inverse side

            // Save the item directly (JPA will handle the relationship via category_id)
            ItemEntity savedItemEntity = itemRepository.save(itemEntity);
            return itemMapper.entityToDomainModelForItem(savedItemEntity);
        } else {
            // Save item without category
            ItemEntity savedItemEntity = itemRepository.save(itemEntity);
            return itemMapper.entityToDomainModelForItem(savedItemEntity);
        }
        }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item updateItem(Long id, ItemDTO itemDTO) {
        ItemEntity itemEntity = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        itemEntity.setName(itemDTO.getName());
        itemEntity.setPrice(itemDTO.getPrice());
        itemEntity.setStockQuantity(itemDTO.getStockQuantity());
        if(itemDTO.getCategoryId()!=null){
            CategoryEntity categoryEntity = categoryRepository.findById(itemDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
            itemEntity.setCategoryEntity(categoryEntity);
        }
        ItemEntity updatedEntity = itemRepository.save(itemEntity);

        return itemMapper.entityToDomainModelForItem(updatedEntity);
    }

    public PurchaseData buyItem(Long id, int quantity){
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(
                ()
                -> new RuntimeException("Item not found.")
        );
        if(itemEntity.getStockQuantity()<quantity){
            throw new RuntimeException("Not enough stock available you choose "+ quantity +
                    " items but there is only" + itemEntity.getStockQuantity() + " items");
        }

        double totalCost = itemEntity.getPrice() * quantity;

        itemEntity.setStockQuantity(itemEntity.getStockQuantity()-quantity);
        itemRepository.save(itemEntity);

        return new PurchaseData(itemEntity.getStockQuantity(),totalCost);


    }
}
