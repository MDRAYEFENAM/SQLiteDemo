package com.example.sqlitedemo.adapter;

import android.content.Context;
import android.media.ImageWriter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.model.Student;

import java.util.List;

public class DisplayAdapter extends BaseAdapter {
    Context context;
    List<Student> studentList;

    public DisplayAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.item_layout,null);

        TextView tVUserName = customView.findViewById(R.id.textViewUsername);
        TextView tVCGPA = customView.findViewById(R.id.textViewCGPA);
       // ImageView callIcon = customView.findViewById(R.id.imageViewPhone);

        tVUserName.setText(studentList.get(position).getName());
        tVCGPA.setText(studentList.get(position).getCgpa()+"");

        return customView;
    }
}
