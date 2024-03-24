package com.example.TodoApiSpring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {
    private static List<Todo> todoList;
    public TodoController(){
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,false,"todo 1",1));
        todoList.add(new Todo(2,true,"todo 2",2));
    }

@GetMapping("/todos")
    public List<Todo> getTodos() {

        return todoList;
}

@PostMapping("/todos")
@ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return newTodo;
}


}