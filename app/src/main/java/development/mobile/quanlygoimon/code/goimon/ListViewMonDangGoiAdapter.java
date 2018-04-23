package development.mobile.quanlygoimon.code.goimon;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import development.mobile.quanlygoimon.code.R;
import development.mobile.quanlygoimon.code.entity.MonAn;

public class ListViewMonDangGoiAdapter extends ArrayAdapter<MonAn> {
    private Activity context = null;
    private int layoutID;
    private List<MonAn> monAnLst = null;

    public ListViewMonDangGoiAdapter(Activity context, int textViewResourceId, List<MonAn> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.layoutID = textViewResourceId;
        this.monAnLst = objects;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);

        if (monAnLst.size() > 0 && position >= 0) {
            final TextView tenMonTxtView_item = (TextView) convertView.findViewById(R.id.tenMonTxtView_item);
            final TextView giaTxtView_item = (TextView) convertView.findViewById(R.id.giaTxtView_item);
            final TextView ghiChuTxtView_item = (TextView) convertView.findViewById(R.id.ghiChuTxtView_item);
            final NumberPicker numPicker_item = (NumberPicker) convertView.findViewById(R.id.numPicker_item);
            final MonAn monAn = monAnLst.get(position);

            tenMonTxtView_item.setText(monAn.getTenMonAn());
            giaTxtView_item.setText(NumberFormat.getCurrencyInstance().format(monAn.getGia()));
            ghiChuTxtView_item.setText(monAn.getGhiChu());
            numPicker_item.setMaxValue(100);
            numPicker_item.setMinValue(0);
            numPicker_item.setTag(position);
            numPicker_item.setValue(monAn.getSoLuong());
            numPicker_item.setWrapSelectorWheel(false);

            numPicker_item.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    if (newVal == 0) {
                        monAnLst.remove(position);
                        notifyDataSetChanged();
                    } else {
                        monAnLst.get(position).setSoLuong(newVal);
                    }
                }
            });
        }

        return convertView;
    }
}
