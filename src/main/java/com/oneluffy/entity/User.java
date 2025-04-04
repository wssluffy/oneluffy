package com.oneluffy.entity;

import lombok.Data;

;import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String address;
    private String phone;
    private String email;
    private Date createdAt;
    private Date updatedAt;
}
