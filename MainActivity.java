package com.example.quanlysinhvienapp;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText edtTen, edtDiem;
    Button btnThem, btnSapXep, btnMax, btnTrungBinh;
    ListView lvSinhVien;
    ArrayList<SinhVien> dsSinhVien;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTen = findViewById(R.id.edtTen);
        edtDiem = findViewById(R.id.edtDiem);
        btnThem = findViewById(R.id.btnThem);
        btnSapXep = findViewById(R.id.btnSapXep);
        btnMax = findViewById(R.id.btnMax);
        btnTrungBinh = findViewById(R.id.btnTrungBinh);
        lvSinhVien = findViewById(R.id.lvSinhVien);

        dsSinhVien = new ArrayList<>();
        taoDanhSachNgauNhien();

        capNhatListView();

        btnThem.setOnClickListener(v -> themSinhVien());
        btnSapXep.setOnClickListener(v -> sapXepAZ());
        btnMax.setOnClickListener(v -> timDiemCaoNhat());
        btnTrungBinh.setOnClickListener(v -> tinhDiemTrungBinh());
    }

    private void taoDanhSachNgauNhien() {
        String[] tenMau = {
                "Nguyễn Văn A", "Trần Thị B", "Lê Minh C", "Phạm Hồng D", "Võ Thanh E",
                "Đỗ Quang F", "Ngô Đức G", "Huỳnh Lan H", "Phan Quốc I", "Trương Mỹ K",
                "Lâm Hữu L", "Tạ Hoàng M", "Nguyễn Thị N", "Lê Hồng O", "Phan Trung P",
                "Trần Thị Q", "Võ Văn R", "Đặng Mai S", "Ngô Thanh T", "Bùi Văn U"
        };

        Random rd = new Random();
        for (String ten : tenMau) {
            double diem = Math.round((5 + rd.nextDouble() * 5) * 10.0) / 10.0; // 5.0 - 10.0, 1 chữ số thập phân
            dsSinhVien.add(new SinhVien(ten, diem));
        }
    }

    private void capNhatListView() {
        ArrayList<String> listTen = new ArrayList<>();
        for (SinhVien sv : dsSinhVien) {
            listTen.add(sv.getTen() + " - Điểm: " + sv.getDiem());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTen);
        lvSinhVien.setAdapter(adapter);
    }

    private void themSinhVien() {
        String ten = edtTen.getText().toString().trim();
        String diemStr = edtDiem.getText().toString().trim();

        if (ten.isEmpty() || diemStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double diem = Double.parseDouble(diemStr);
            diem = Math.round(diem * 10.0) / 10.0;
            dsSinhVien.add(new SinhVien(ten, diem));
            capNhatListView();
            edtTen.setText("");
            edtDiem.setText("");
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Điểm phải là số!", Toast.LENGTH_SHORT).show();
        }
    }

    private void sapXepAZ() {
        Collections.sort(dsSinhVien, Comparator.comparing(SinhVien::getTen));
        capNhatListView();
    }

    private void timDiemCaoNhat() {
        if (dsSinhVien.isEmpty()) return;
        SinhVien max = Collections.max(dsSinhVien, Comparator.comparingDouble(SinhVien::getDiem));
        Toast.makeText(this, "Sinh viên cao điểm nhất: " + max.getTen() + " (" + max.getDiem() + ")", Toast.LENGTH_LONG).show();
    }

    private void tinhDiemTrungBinh() {
        if (dsSinhVien.isEmpty()) return;
        double tong = 0;
        for (SinhVien sv : dsSinhVien) tong += sv.getDiem();
        double tb = Math.round((tong / dsSinhVien.size()) * 10.0) / 10.0;
        Toast.makeText(this, "Điểm trung bình: " + tb, Toast.LENGTH_LONG).show();
    }
}
