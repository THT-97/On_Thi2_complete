package vn.edu.ntu.quangloc.on_thi2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.quangloc.controller.IContactController;
import vn.edu.ntu.quangloc.model.Contact;

public class SecondFragment extends Fragment {

    EditText edtId, edtName, edtDate, edtPhone, edtAddr;
    Button btnAdd;
    IContactController controller;
    NavController navController;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        addViews(view);
        addActions();
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(SecondFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
//            }
//        });
    }

    private void addActions() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact();
                contact.setId(controller.layId());
                contact.setName(edtName.getText().toString());
                contact.setBirthday(edtDate.getText().toString());
                contact.setPhone(edtPhone.getText().toString());
                contact.setAddress(edtAddr.getText().toString());
                if(controller.getCurrent()>0){
                    controller.getAllContact().set(controller.getCurrent()-1, contact);
                }
                else controller.addContact(contact);
                Toast.makeText(getActivity(), "Đã lưu", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void addViews(View view) {
        edtId = view.findViewById(R.id.edtId);
        edtName = view.findViewById(R.id.edtName);
        edtDate = view.findViewById(R.id.edtDate);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtAddr = view.findViewById(R.id.edtAddr);
        btnAdd = view.findViewById(R.id.btnAdd);

        navController = NavHostFragment.findNavController(SecondFragment.this);
        ((MainActivity)getActivity()).navController = navController;
        ((MainActivity)getActivity()).toolbar.setSubtitle("Detail of Friends");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        controller = (IContactController) ((MainActivity)getActivity()).getApplication();
        if(controller.getCurrent()>0) edtId.setText(Integer.toString(controller.getCurrent()));
        else edtId.setText(Integer.toString(controller.layId()));
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}