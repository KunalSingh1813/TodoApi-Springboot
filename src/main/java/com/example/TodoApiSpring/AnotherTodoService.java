package com.example.TodoApiSpring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("anotherTodoService")
public class AnotherTodoService implements TodoService{

    @Override
    public String doSomething(){
        return "Something from another todoService";
    }
}
