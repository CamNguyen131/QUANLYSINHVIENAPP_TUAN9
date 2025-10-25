package com.example.quanlysinhvienapp;

public class SinhVien {
    private String ten;
    private double diem;

    public SinhVien(String ten, double diem) {
        this.ten = ten;
        this.diem = diem;
    }

    public String getTen() {
        return ten;
    }

    public double getDiem() {
        return diem;
    }
}
