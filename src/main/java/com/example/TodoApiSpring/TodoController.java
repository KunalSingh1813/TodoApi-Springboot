package com.example.TodoApiSpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {
    private static List<Todo> todoList;

    //Error message
    private static final String TODO_NOT_FOUND = "Todo not found";
    public TodoController(){
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,false,"todo 1",1));
        todoList.add(new Todo(2,true,"todo 2",2));
    }

  @GetMapping("/todos")
  public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false, defaultValue = "true") Boolean isCompleted){
        System.out.println("incoming query params: "+ isCompleted);
        return ResponseEntity.ok(todoList);
  }
/*
@GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos() {

        return ResponseEntity.ok(todoList);
}
*/
@PostMapping("/todos")

    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){
        /*
         * we can use this annotation to set the status code @ResponseStatus(HttpStatus.CREATED)
         */
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
}


/*
 @GetMapping("/todos/{todoId}")
 public ResponseEntity<Todo> getTodoById(@PathVariable Long todoId){
    for(Todo todo: todoList){
        if(todo.getId()==todoId){
            return ResponseEntity.ok(todo);
        }
    }
    return ResponseEntity.notFound().build();
 }
 */

 //practice task: add a custom message along with 404 not found
 @GetMapping("/todos/{todoId}")
 public ResponseEntity getTodoById(@PathVariable Long todoId){

     for(Todo todo: todoList){
         if(todo.getId()==todoId){
             return ResponseEntity.ok(todo);
         }
     }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
     }

 }

