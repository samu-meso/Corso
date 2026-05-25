package com.example.dataapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewAdapter extends ArrayAdapter<Persona>
{
    private int layoutId;

    public NewAdapter(Context context,
                             int layoutId,
                             List<Persona> objects)
    {
        super(context, layoutId, objects);

        this.layoutId=layoutId;
    }

    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent)
    {
        ViewHolder viewHolder;

        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(this.layoutId, null);

            viewHolder=new ViewHolder();
            viewHolder.info=convertView.findViewById(R.id.infoTextView);
            //viewHolder.anni=convertView.findViewById(R.id.textViewAge);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        Persona persona=getItem(position);
        viewHolder.info.setText("INFO: " + persona.getNome());

        return convertView;
    }

    private class ViewHolder
    {
        public TextView info;
    }
}
