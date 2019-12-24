package com.taxi.managerstudent;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.taxi.managerstudent.models.SubjectResponse;

import java.util.List;

public class SpinnerSubjectAdapter extends ArrayAdapter<SubjectResponse> {
    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private List<SubjectResponse> values;

    public SpinnerSubjectAdapter(Context context, int textViewResourceId,
                                 List<SubjectResponse> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values == null ? 0 : values.size();
    }

    @Override
    public SubjectResponse getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        convertView = LayoutInflater.from(context).inflate(R.layout.item_spinner, null);
        TextView label = (TextView) convertView.findViewById(R.id.item_spn_tv_title);
        TextView content = (TextView) convertView.findViewById(R.id.item_spn_tv_content);
        label.setText(values.get(position).getSubjectName());
        content.setText(values.get(position).getSubjectCode());
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_spinner, null);
        TextView label = (TextView) convertView.findViewById(R.id.item_spn_tv_title);
        TextView content = (TextView) convertView.findViewById(R.id.item_spn_tv_content);
        label.setText(values.get(position).getSubjectName());
        content.setText(values.get(position).getSubjectCode());

        return label;
    }

}
