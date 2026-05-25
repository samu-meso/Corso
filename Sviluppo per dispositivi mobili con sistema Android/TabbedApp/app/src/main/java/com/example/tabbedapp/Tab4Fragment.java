package com.example.tabbedapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab4Fragment extends Fragment {


    private ListView emailsList;

    private ArrayList<String> emails;
    private ArrayAdapter<String> adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab4Fragment newInstance(String param1, String param2) {
        Tab4Fragment fragment = new Tab4Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        emails = new ArrayList<>();
        emails.add("Email 1");
        emails.add("Email 2");
        emails.add("Email 3");
        emails.add("Email 1");
        emails.add("Email 2");
        emails.add("Email 3");
        emails.add("Email 1");
        emails.add("Email 2");
        emails.add("Email 3");
        emails.add("Email 1");
        emails.add("Email 2");
        emails.add("Email 3");
        emails.add("Email 1");
        emails.add("Email 2");
        emails.add("Email 3");
        emails.add("Email 1");
        emails.add("Email 2");
        emails.add("Email 3");
        emails.add("Email 1");
        emails.add("Email 2");
        emails.add("Email 3");
        emails.add("Email 1");
        emails.add("Email 2");
        emails.add("Email 3");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab4, container, false);

        emailsList = view.findViewById(R.id.emailsList);

        adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                emails
        );

        emailsList.setAdapter(adapter);

        emailsList.setOnItemClickListener((adapterView, view1, position, id) -> {
                String emailSelezionata = emails.get(position);
                System.out.println(emailSelezionata);
            //            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//
//            Log.d("ONITEMCLICK", nomi.get(position));
//            Log.d("ONITEMCLICK",String.valueOf(position));
//
//            intent.putExtra("nome", nomi.get(position));
//            intent.putExtra(LIST_POSITION, position);
//
//            startActivity(intent);
        });

        return view;
    }

}