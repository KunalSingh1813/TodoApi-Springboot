package com.example.TodoApiSpring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("fakeTodoService")
public class FakeTodoService implements  TodoService{

    @TimeMonitor

    public String doSomething(){
        for(int i=0;i<1000000000 ;i++){};
        return "Something";
    } //join point
}
