package com.example.TodoApiSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {




    private TodoService todoService; //anotherTodoService     this is called composition

    private TodoService todoService2; //fakeTodoService
    private static List<Todo> todoList;

    //Error message
    private static final String TODO_NOT_FOUND = "Todo not found";
    public TodoController(
            @Qualifier("anotherTodoService") TodoService todoService,
            @Qualifier("fakeTodoService") TodoService todoService2){
            this.todoService = todoService;
            this.todoService = todoService2;
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,false,"todo 1",1));
        todoList.add(new Todo(2,true,"todo 2",2));
    }

  @GetMapping
  public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false, defaultValue = "true") Boolean isCompleted){
        System.out.println("incoming query params: "+ isCompleted + " " + this.todoService.doSomething());
        return ResponseEntity.ok(todoList);
  }
/*
@GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos() {

        return ResponseEntity.ok(todoList);
}
*/
@PostMapping

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
 @GetMapping("/{todoId}")
 public ResponseEntity getTodoById(@PathVariable Long todoId){

     for(Todo todo: todoList){
         if(todo.getId()==todoId){
             return ResponseEntity.ok(todo);
         }
        }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
     }

 }

