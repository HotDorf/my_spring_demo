package com.example.annotation.springInjection;

import com.example.annotation.springContextAweraTest.SpringContextAweraTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDao {

    public void saveMoney(){
        System.out.println("i have saved money!");
    }

}
