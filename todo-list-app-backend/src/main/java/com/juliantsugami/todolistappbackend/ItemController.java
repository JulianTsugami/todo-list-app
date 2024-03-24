package com.juliantsugami.todolistappbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(path="/")
    public @ResponseBody Iterable<Item> getTodoList() {
        // This returns a JSON or XML with the users
        return itemRepository.findAll();
    }
}