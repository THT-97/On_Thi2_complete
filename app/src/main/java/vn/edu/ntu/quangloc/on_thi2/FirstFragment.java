package vn.edu.ntu.quangloc.on_thi2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.quangloc.controller.IContactController;
import vn.edu.ntu.quangloc.model.Contact;

public class FirstFragment extends Fragment implements View.OnClickListener {

    List<Contact> listContacts = new ArrayList<>();
    RecyclerView rvListContact;
    ContactAdapter adapter;
    Button btnAdd;
    IContactController controller;
    NavController navController;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        addViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
//        view.findViewById(R.id.itemAdd).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }

    private void addViews(View view) {
        rvListContact = view.findViewById(R.id.rvListContact);
        controller = (IContactController) ((MainActivity)getActivity()).getApplication();
        listContacts = controller.getAllContact();
        adapter = new ContactAdapter(listContacts);

        navController = NavHostFragment.findNavController(FirstFragment.this);
        ((MainActivity)getActivity()).navController = navController;
        ((MainActivity)getActivity()).toolbar.setSubtitle("List of All Friends");

        rvListContact.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvListContact.setAdapter(adapter);

        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        controller.setCurrent(-1);
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment);
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtDate, txtPhone;
        ImageView imvEdit;
        Contact contact;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            imvEdit = itemView.findViewById(R.id.imvEdit);
            imvEdit.setOnClickListener(this);
        }

        public void bind(Contact contact) {
            this.contact = contact;
            txtName.setText(contact.getName());
            txtDate.setText(contact.getBirthday());
            txtPhone.setText(contact.getPhone());
        }

        @Override
        public void onClick(View view) {
            controller.setCurrent(listContacts.indexOf(contact));
            NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
        }
    }

    public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

        List<Contact> listContacts = new ArrayList<>();

        public ContactAdapter(List<Contact> listContacts) {
            this.listContacts = listContacts;
        }

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.contact, parent, false);
            return new ContactViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            holder.bind(listContacts.get(position));
        }

        @Override
        public int getItemCount() {
            return listContacts.size();
        }
    }

}