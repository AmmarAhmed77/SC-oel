/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.todolistapp;


import java.util.ArrayList;
import java.util.List;

class TodoList {
    private List<TodoItem> items;

    public TodoList() {
        this.items = new ArrayList<>();
    }

    public void addItem(TodoItem item) {
        items.add(item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public void editItem(int index, String newDescription) {
        if (index >= 0 && index < items.size()) {
            TodoItem item = items.get(index);
            item.setDescription(newDescription);
        }
    }

    public void markItemAsCompleted(int index) {
        if (index >= 0 && index < items.size()) {
            TodoItem item = items.get(index);
            item.markAsCompleted();
        }
    }

    public void markItemAsIncomplete(int index) {
        if (index >= 0 && index < items.size()) {
            TodoItem item = items.get(index);
            item.markAsIncomplete();
        }
    }

    public List<TodoItem> getItems() {
        return items;
    }
}