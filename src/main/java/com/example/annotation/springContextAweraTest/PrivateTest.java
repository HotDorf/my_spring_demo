package com.example.annotation.springContextAweraTest;

import com.example.annotation.springDataJPA.entity.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Vector;

@Data
@Component
public class PrivateTest {

    private String str;

    public void t01(){
        str = "sa";
        System.out.println(this.str);
        LinkedList<User> users = new LinkedList<>();
        Vector<User> users1 = new Vector<>();

    }


}
