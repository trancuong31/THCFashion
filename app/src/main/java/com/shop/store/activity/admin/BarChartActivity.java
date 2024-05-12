package com.shop.store.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.shop.store.R;
import com.shop.store.databinding.ActivityBarChartBinding;
import com.shop.store.dbhelper.BillDbHelper;
import com.shop.store.dbhelper.UserDbHelper;
import com.shop.store.model.JsonStatusCart;
import com.shop.store.model.Bill;
import com.shop.store.model.User;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {
    ArrayList<Bill> billArrayList = new ArrayList<>();
    ArrayList<Bill> bills = new ArrayList<>();
    ArrayList<Bill> billUnArrayList = new ArrayList<>();
    private ActivityBarChartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBarChartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        thongKeBill();
        binding.btnSanPham.setOnClickListener(v -> thongKeBill());

        binding.btnThongkeUser.setOnClickListener(v -> thongKeUser());

        binding.btnMuaHang.setOnClickListener(v -> thongKeMuahang());
        binding.btnThongKeTongThuNhap.setOnClickListener(v -> thongKeThuNhap());
    }

    public void thongKeUser() {
        UserDbHelper userDbHelper = new UserDbHelper(this);
        ArrayList<User> user = userDbHelper.getAllUser();
        ArrayList<User> userKhoa = new ArrayList<>();
        ArrayList<User> userMo = new ArrayList<>();

        for (User user1 : user) {
            if (user1.getStatus().equals("active")) {
                userMo.add(user1);
            } else {
                userKhoa.add(user1);
            }
        }

        BarChart barChart = findViewById(R.id.barChart);
        barChart.clear();
        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(0, userMo.size()));
        visitors.add(new BarEntry(1, userKhoa.size()));
        visitors.add(new BarEntry(2, user.size()));
        final ArrayList<String> axiss = new ArrayList<>();
        axiss.add("Tài khoản active");
        axiss.add("Tài khoản unactive");
        axiss.add("Tổng");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(0);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(axiss));

        BarDataSet barDataSet = new BarDataSet(visitors, "Visitors");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Thống kê Tài khoản");
        barChart.animateY(2000);

    }

    public void thongKeBill() {
        getBill();
        BarChart barChart = findViewById(R.id.barChart);
        barChart.clear();
        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(0, billArrayList.size()));
        visitors.add(new BarEntry(1, billUnArrayList.size()));
        visitors.add(new BarEntry(2, bills.size()));
        final ArrayList<String> axiss = new ArrayList<>();
        axiss.add("Bill đã thanh toán");
        axiss.add("Bill chưa thanh toán");
        axiss.add("Tổng Bill");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(0);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(axiss));

        BarDataSet barDataSet = new BarDataSet(visitors, "Visitors");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Thống kê sản phẩm");
        barChart.animateY(2000);
    }


    public void thongKeMuahang() {
        ArrayList<Bill> bills1 = new ArrayList<>();
        ArrayList<Bill> bills2 = new ArrayList<>();
        ArrayList<Bill> bills3 = new ArrayList<>();
        ArrayList<Bill> bills4 = new ArrayList<>();
        ArrayList<Bill> bills5 = new ArrayList<>();
        ArrayList<Bill> bills6 = new ArrayList<>();
        ArrayList<Bill> bills7 = new ArrayList<>();
        ArrayList<Bill> bills8 = new ArrayList<>();
        ArrayList<Bill> bills9 = new ArrayList<>();
        ArrayList<Bill> bills10 = new ArrayList<>();
        ArrayList<Bill> bills11 = new ArrayList<>();
        ArrayList<Bill> bills12 = new ArrayList<>();
        BillDbHelper billDbHelper = new BillDbHelper(this);
        bills = billDbHelper.getAllBills();

        for (Bill bill : bills) {
            String[] time = bill.getDate().split(",");
            String[] timeDate = time[0].split("/");
            String month = timeDate[1];
            String year = timeDate[2].split(" ")[0];
            if (year.equals(binding.edtYear.getText().toString())) {
                if (month.equals("01")) {
                    bills1.add(bill);
                } else if (month.equals("02")) {
                    bills2.add(bill);
                } else if (month.equals("03")) {
                    bills3.add(bill);
                } else if (month.equals("04")) {
                    bills4.add(bill);
                } else if (month.equals("05")) {
                    bills5.add(bill);
                } else if (month.equals("06")) {
                    bills6.add(bill);
                } else if (month.equals("07")) {
                    bills7.add(bill);
                } else if (month.equals("08")) {
                    bills8.add(bill);
                } else if (month.equals("09")) {
                    bills9.add(bill);
                } else if (month.equals("10")) {
                    bills10.add(bill);
                } else if (month.equals("11")) {
                    bills11.add(bill);
                } else if (month.equals("12")) {
                    bills12.add(bill);
                }
            }
        }

        BarChart barChart = findViewById(R.id.barChart);
        barChart.clear();
        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(0, bills1.size()));
        visitors.add(new BarEntry(1, bills2.size()));
        visitors.add(new BarEntry(2, bills3.size()));
        visitors.add(new BarEntry(4, bills4.size()));
        visitors.add(new BarEntry(5, bills5.size()));
        visitors.add(new BarEntry(6, bills6.size()));
        visitors.add(new BarEntry(7, bills7.size()));
        visitors.add(new BarEntry(8, bills8.size()));
        visitors.add(new BarEntry(9, bills9.size()));
        visitors.add(new BarEntry(10, bills10.size()));
        visitors.add(new BarEntry(11, bills11.size()));
        visitors.add(new BarEntry(12, bills12.size()));
        final ArrayList<String> axiss = new ArrayList<>();
        axiss.add("T1");
        axiss.add("T2");
        axiss.add("T3");
        axiss.add("T4");
        axiss.add("T5");
        axiss.add("T6");
        axiss.add("T7");
        axiss.add("T8");
        axiss.add("T9");
        axiss.add("T10");
        axiss.add("T11");
        axiss.add("T12");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(2f);
        xAxis.setLabelRotationAngle(0);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(axiss));

        BarDataSet barDataSet = new BarDataSet(visitors, "Visitors");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(12f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Thống kê mua hàng");
        barChart.animateY(2000);
    }

    public void thongKeThuNhap() {
        ArrayList<Bill> bills1 = new ArrayList<>();
        ArrayList<Bill> bills2 = new ArrayList<>();
        ArrayList<Bill> bills3 = new ArrayList<>();
        ArrayList<Bill> bills4 = new ArrayList<>();
        ArrayList<Bill> bills5 = new ArrayList<>();
        ArrayList<Bill> bills6 = new ArrayList<>();
        ArrayList<Bill> bills7 = new ArrayList<>();
        ArrayList<Bill> bills8 = new ArrayList<>();
        ArrayList<Bill> bills9 = new ArrayList<>();
        ArrayList<Bill> bills10 = new ArrayList<>();
        ArrayList<Bill> bills11 = new ArrayList<>();
        ArrayList<Bill> bills12 = new ArrayList<>();
        BillDbHelper billDbHelper = new BillDbHelper(this);
        bills = billDbHelper.getAllBills();

        for (Bill bill : bills) {
            String[] time = bill.getDate().split(",");
            String[] timeDate = time[0].split("/");
            String month = timeDate[1];
            String year = timeDate[2].split(" ")[0];
            if (year.equals(binding.edtYear.getText().toString())) {
                if (month.equals("01")) {
                    bills1.add(bill);
                } else if (month.equals("02")) {
                    bills2.add(bill);
                } else if (month.equals("03")) {
                    bills3.add(bill);
                } else if (month.equals("04")) {
                    bills4.add(bill);
                } else if (month.equals("05")) {
                    bills5.add(bill);
                } else if (month.equals("06")) {
                    bills6.add(bill);
                } else if (month.equals("07")) {
                    bills7.add(bill);
                } else if (month.equals("08")) {
                    bills8.add(bill);
                } else if (month.equals("09")) {
                    bills9.add(bill);
                } else if (month.equals("10")) {
                    bills10.add(bill);
                } else if (month.equals("11")) {
                    bills11.add(bill);
                } else if (month.equals("12")) {
                    bills12.add(bill);
                }
            }
        }

        int tong = 0;
        int tong1 = 0;
        int tong2 = 0;
        int tong3 = 0;
        int tong4 = 0;
        int tong5 = 0;
        int tong6 = 0;
        int tong7 = 0;
        int tong8 = 0;
        int tong9 = 0;
        int tong10 = 0;
        int tong11 = 0;

        for (Bill bill : bills1) {
            tong += bill.getPrice();
        }

        for (Bill bill : bills2) {
            tong1 += bill.getPrice();
        }

        for (Bill bill : bills3) {
            tong2 += bill.getPrice();
        }

        for (Bill bill : bills4) {
            tong3 += bill.getPrice();
        }

        for (Bill bill : bills5) {
            tong4 += bill.getPrice();
        }

        for (Bill bill : bills6) {
            tong5 += bill.getPrice();
        }

        for (Bill bill : bills7) {
            tong6 += bill.getPrice();
        }

        for (Bill bill : bills8) {
            tong7 += bill.getPrice();
        }

        for (Bill bill : bills9) {
            tong8 += bill.getPrice();
        }

        for (Bill bill : bills10) {
            tong9 += bill.getPrice();
        }

        for (Bill bill : bills11) {
            tong10 += bill.getPrice();
        }

        for (Bill bill : bills12) {
            tong11 += bill.getPrice();
        }

        BarChart barChart = findViewById(R.id.barChart);
        barChart.clear();
        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(0, tong));
        visitors.add(new BarEntry(1, tong1));
        visitors.add(new BarEntry(2, tong2));
        visitors.add(new BarEntry(4, tong3));
        visitors.add(new BarEntry(5, tong4));
        visitors.add(new BarEntry(6, tong5));
        visitors.add(new BarEntry(7, tong6));
        visitors.add(new BarEntry(8, tong7));
        visitors.add(new BarEntry(9, tong8));
        visitors.add(new BarEntry(10, tong9));
        visitors.add(new BarEntry(11, tong10));
        visitors.add(new BarEntry(12, tong11));
        final ArrayList<String> axiss = new ArrayList<>();
        axiss.add("T1");
        axiss.add("T2");
        axiss.add("T3");
        axiss.add("T4");
        axiss.add("T5");
        axiss.add("T6");
        axiss.add("T7");
        axiss.add("T8");
        axiss.add("T9");
        axiss.add("T10");
        axiss.add("T11");
        axiss.add("T12");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(2f);
        xAxis.setLabelRotationAngle(0);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(axiss));

        BarDataSet barDataSet = new BarDataSet(visitors, "Visitors");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(12f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Thống kê mua hàng");
        barChart.animateY(2000);
    }


    public void getBill() {
        billUnArrayList.clear();
        billArrayList.clear();
        BillDbHelper billDbHelper = new BillDbHelper(this);
        bills = billDbHelper.getAllBills();
        if (!bills.isEmpty()) {
            for (Bill bill : bills) {
                String[] status = bill.getStatus().split(",");
                if (status[0].equals("Paid")) {
                    billArrayList.add(bill);
                } else {
                    JsonStatusCart jsonStatusCart = new Gson().fromJson(status[1] + "," + status[2] + "," + status[3], JsonStatusCart.class);
                    if (!jsonStatusCart.getStatus().equals("Paid"))
                        billUnArrayList.add(bill);
                }
            }
        }
    }

}