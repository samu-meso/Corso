/*package it.esempiandroid.asynctest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PersonaAdapterTop extends ArrayAdapter<Persona>
{
    //private int layoutId;
    //private int row;

    public PersonaAdapterTop(Context context,
                             int layoutId,
                             List<Persona> objects)
    {
        //super(context, layoutId, objects);
        super(context, 0, objects);

        //this.layoutId=layoutId;
        //this.row = R.layout.row;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType=0;

        if(position%2!=0)
            viewType=1;

        return viewType;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent)
    {
        int viewType = this.getItemViewType(position);
        Log.d("position", position + "");
        Log.d("viewType", viewType+"");
        View v3 = convertView;

        switch(viewType)
        {
            case 0:

                ViewHolder holder1;

                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getContext().getSystemService     (Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.rowcustom, parent, false);

                    holder1 = new ViewHolder ();
                    holder1.nome = v.findViewById(R.id.textViewName);
                    holder1.anni = v.findViewById(R.id.textViewAge);
                    v.setTag(holder1);
                }
                else {
                    holder1 = (ViewHolder)v.getTag();
                }

                Persona persona=getItem(position);

                //Log.e("holder1.type: ", holder1.type);

                holder1.nome.setText(persona.getID() + "" + persona.getNome() + " " + persona.getCognome());
                holder1.anni.setText(persona.getAnni());

                return v;
            case 1:
                ViewHolder2 holder2;

                View v2 = convertView;
                if (v2 == null) {
                    LayoutInflater vi1 = (LayoutInflater)getContext().getSystemService     (Context.LAYOUT_INFLATER_SERVICE);
                    v2 = vi1.inflate(R.layout.row, parent, false);

                    holder2 = new ViewHolder2 ();
                    holder2.info = v2.findViewById(R.id.infoTextView);
                    v2.setTag(holder2);
                }
                else {
                    holder2 = (ViewHolder2)v2.getTag();
                }

                Persona persona2=getItem(position);
                holder2.info.setText("INFO: " + persona2.getID() + persona2.getNome() + " " + persona2.getCognome()  +persona2.getAnni());
                return v2;
            default:
                //?
        }

        return v3;
    }

    private class ViewHolder
    {
        public TextView nome;
        public TextView anni;
    }

    private class ViewHolder2
    {
        public TextView info;
    }
}*/


package com.example.dataapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PersonaAdapterTop extends ArrayAdapter<Persona>
{
    private int layoutId;

    public PersonaAdapterTop(Context context,
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
        Log.d("position", position + "");

        ViewHolder viewHolder;

        if(convertView==null)
        {
            Log.d("position", position + "inner");

            LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView=inflater.inflate(this.layoutId, null);

            viewHolder=new ViewHolder();

            viewHolder.nome = convertView.findViewById(R.id.textViewName);
            viewHolder.anni = convertView.findViewById(R.id.textViewAge);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        Persona persona=getItem(position);

        //Log.e("viewHolder.type: ", viewHolder.type);

        viewHolder.nome.setText(persona.getNome() + " " + persona.getCognome());
        viewHolder.anni.setText(persona.getAnni());

        return convertView;
    }

    private class ViewHolder
    {
        public TextView nome;
        public TextView anni;
    }
}
