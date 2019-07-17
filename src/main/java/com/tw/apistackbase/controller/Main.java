package com.tw.apistackbase.controller;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee(5, "dd", 33, "man",100);
        JSONObject jsonObject = new JSONObject(employee);
        System.out.println(jsonObject.toString());
    }
}
