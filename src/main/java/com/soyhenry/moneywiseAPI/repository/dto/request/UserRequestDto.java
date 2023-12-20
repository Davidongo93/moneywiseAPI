package com.soyhenry.moneywiseAPI.repository.dto.request;
// Esta es la definicion del objeto que el usuario envia provisionalmente por Http por medio de JSON
public class  UserRequestDto {
    private String name;
    private String email;
    private String pass;

    public UserRequestDto(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public UserRequestDto() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public String getPass() {
        return pass;
    }

}
