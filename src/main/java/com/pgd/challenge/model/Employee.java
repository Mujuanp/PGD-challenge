package com.pgd.challenge.model;

public class Employee {

    private int Id;
    private String name;
    private float monthlyAmount;

    private boolean active;

    public Employee(int id, String name, float monthlyAmount, boolean active) {
        Id = id;
        this.name = name;
        this.monthlyAmount = monthlyAmount;
        this.active = active;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(float monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
