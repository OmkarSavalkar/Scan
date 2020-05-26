package com.example.myscan.modelscanstore;

public class Listitem
{
    int id;
    String code;
    public String type;

    public Listitem(int id, String code, String type) {
        this.id = id;
        this.code = code;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

}
