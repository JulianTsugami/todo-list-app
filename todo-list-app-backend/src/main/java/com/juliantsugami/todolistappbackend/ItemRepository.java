package com.juliantsugami.todolistappbackend;

import org.springframework.data.repository.CrudRepository;

import  com.juliantsugami.todolistappbackend.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {

}