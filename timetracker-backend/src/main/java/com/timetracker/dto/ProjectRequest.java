package com.timetracker.dto;

public class ProjectRequest {
    private String name;
    private String client;
    private String color;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}