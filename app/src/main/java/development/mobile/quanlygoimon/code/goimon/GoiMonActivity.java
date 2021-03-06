package development.mobile.quanlygoimon.code.goimon;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;

import development.mobile.quanlygoimon.code.R;
import development.mobile.quanlygoimon.code.entity.MonAn;
import development.mobile.quanlygoimon.code.goimon.DSNhomHangFragment.SendTenNhomHang;
import development.mobile.quanlygoimon.code.goimon.DSMonAnFragment.SendMonAn;


public class GoiMonActivity extends AppCompatActivity implements SendTenNhomHang, SendMonAn {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private ArrayList<String> banSelected = new ArrayList<String>();
    private TextView dsBanTxtView, soBanTxtView, tongTienTxtView;
    private double tongTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goimon);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dsBanTxtView = (TextView) findViewById(R.id.dsBanTxtView);
        tongTienTxtView = (TextView) findViewById(R.id.tongTienTxtView);
        soBanTxtView = (TextView) findViewById(R.id.soBanTxtView);
        tongTienTxtView.setText(NumberFormat.getCurrencyInstance().format(tongTien));
        getDataFromPhucVuActivity();
    }

    private void getDataFromPhucVuActivity(){
        banSelected = getIntent().getStringArrayListExtra("DSBan");
        int sizeLst = banSelected.size();
        for (int i = 0; i < sizeLst; i++){
            if(i < sizeLst - 1){
                dsBanTxtView.setText(dsBanTxtView.getText() + banSelected.get(i) + ", ");
            }
            else{
                dsBanTxtView.setText(dsBanTxtView.getText() + banSelected.get(i));
            }
        }
        soBanTxtView.setText(sizeLst+ "");
    }

    @Override
    public void sendMonAn(MonAn monAn) {
        DSMonDangGoiFragment dsMonDangGoiFrag = (DSMonDangGoiFragment) getSupportFragmentManager().findFragmentById(R.id.monDangGoiFrag);
        dsMonDangGoiFrag.getMonAnFromDSMonAnFrag(monAn);
        tongTien += monAn.getGia();
        tongTienTxtView.setText(NumberFormat.getCurrencyInstance().format(tongTien));
    }

    @Override
    public void senTenNhomHang(String tenNhomHang) {
        DSMonAnFragment dsMonAnFrag = (DSMonAnFragment) getSupportFragmentManager().findFragmentById(R.id.monAnFrag);
        dsMonAnFrag.getTenNhomHangFromDSNhomHangFrag(tenNhomHang);
    }
}
