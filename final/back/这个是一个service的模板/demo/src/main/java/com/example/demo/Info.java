package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.io.Serializable;
public class Info implements Serializable {
    @JsonProperty(value = "fname")
    private String f;
    @JsonProperty(value = "lname")
    private String l;

    public String getFname() {
        return f;
    }

    public String getLname() {
        return l;
    }
}
