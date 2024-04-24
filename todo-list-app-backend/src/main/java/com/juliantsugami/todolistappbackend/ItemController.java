package com.juliantsugami.todolistappbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(path = "/v1/items")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @GetMapping(path = { "", "/" })
    public @ResponseBody Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Item> getItem(@PathVariable("id") Integer id) throws Exception {
        if (!itemRepository.existsById(id)) {
            throw new Exception("Item not found with id " + id);
        }
        return itemRepository.findById(id);
    }

    @PostMapping(path = { "", "/" })
    public @ResponseBody Item createItem(@RequestParam String description) {
        Item newItem = new Item();
        newItem.setDescription(description);
        newItem.setIsDone(false);
        Date now = new Date();
        newItem.setCreatedAt(now);
        newItem.setUpdatedAt(now);
        itemRepository.save(newItem);

        return newItem;
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody Optional<Item> updateItem(@PathVariable("id") Integer id,
            @RequestParam String description, @RequestParam Boolean isDone) throws Exception {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new Exception("Item not found with id " + id));

        if (!existingItem.getDescription().equals(description) || !existingItem.getIsDone().equals(isDone)){
            existingItem.setDescription(description);
            existingItem.setIsDone(isDone);
            existingItem.setUpdatedAt(new Date());

            return Optional.of(itemRepository.save(existingItem));
        }

        return Optional.empty();
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteItem(@PathVariable("id") Integer id) throws Exception {
        if (!itemRepository.existsById(id)) {
            throw new Exception("Item not found with id " + id);
        }
        itemRepository.deleteById(id);
        return "Deleted";
    }
}