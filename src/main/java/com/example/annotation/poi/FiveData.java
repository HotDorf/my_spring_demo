package com.example.annotation.poi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiveData {
    private int age;

    private String sex;

    private boolean isSingle;

  //  private Date birthday;
}
