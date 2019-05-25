package com.yourname.Service;

import com.yourname.Entity.TodoItem;
import com.yourname.Exception.TodoItemNotFoundException;
import com.yourname.Repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemService {

    @Autowired
    public TodoItemRepository todoItemRepository;

    public List<TodoItem> getAllItems(){
        return todoItemRepository.findAll();
    }

    public List<TodoItem> addItem(TodoItem t){
        this.todoItemRepository.save(t);
        return todoItemRepository.findAll();
    }

    public void removeItem(Integer id) throws TodoItemNotFoundException {
        if(this.todoItemRepository.findById(id).isPresent()){
            TodoItem temp = this.todoItemRepository.findById(id).get();
            this.todoItemRepository.delete(temp);
        }
        else{
            throw new TodoItemNotFoundException();
        }
    }

    public TodoItem updateItem(TodoItem t) throws TodoItemNotFoundException {
        if(this.todoItemRepository.findById(t.getId()).isPresent()){
            TodoItem temp = this.todoItemRepository.findById(t.getId()).get();
            temp.setText(t.getText());
            this.todoItemRepository.save(temp);
            return temp;
        }
        else{
            throw new TodoItemNotFoundException();
        }
    }
}
