package com.example.dataapp;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Persona implements Parcelable
//public class Persona
{

    private String id;
    private String nome;
    private String cognome;
    private String anni;

    public Persona(String id, String nome, String cognome, String anni)
    {
        this.id=id;
        this.nome=nome;
        this.cognome=cognome;
        this.anni=anni;
    }

    public String getID()
    {
        return id;
    }

    public void setID(String id)
    {
        this.id=id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome=nome;
    }

    public String getCognome()
    {
        return cognome;
    }

    public void setCognome(String cognome)
    {
        this.cognome=cognome;
    }

    public String getAnni()
    {
        return anni;
    }

    @Override
    public String toString()
    {
        //usuper.toString();
        return "ID: " + id + ", NOME: " + nome + ", COGNOME: " + cognome + ", anni: " + anni;
    }
    private Persona(Parcel in)
    {
        id=in.readString();
        nome=in.readString();
        cognome=in.readString();
        anni=in.readString();
    }

    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(id);
        out.writeString(nome);
        out.writeString(cognome);
        out.writeString(anni);
    }

    /*public static final Creator<it.esempiandroid.asynctest.Persona> CREATOR=new Creator<it.esempiandroid.asynctest.Persona>()
    {
        public it.esempiandroid.asynctest.Persona createFromParcel(Parcel in)
        {
            return new it.esempiandroid.asynctest.Persona(in);
        }

        public it.esempiandroid.asynctest.Persona[] newArray(int size)
        {
            return new it.esempiandroid.asynctest.Persona[size];
        }
    };*/

    public static final Creator<Persona> CREATOR=new Creator<Persona>()
    {
        public Persona createFromParcel(Parcel in)
        {
            return new Persona(in);
        }

        public Persona[] newArray(int size)
        {
            return new Persona[size];
        }
    };

    public int describeContents()
    {
        return 0;
    }




    public Persona(JSONObject o){
        try {
            this.id=o.getString("id");
            this.nome=o.getString("nome");
            this.cognome=o.getString("cognome");
            this.anni=o.getString("anni");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method to convert an array of JSON objects into a list of objects
    // User.fromJson(jsonArray);
    public static ArrayList<Persona> fromJson(JSONArray jsonObjects) {
        ArrayList<Persona> users = new ArrayList<Persona>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                users.add(new Persona(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}