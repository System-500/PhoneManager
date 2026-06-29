package pl.pollub.lab2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.sax.Element;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.pollub.lab2.databinding.PhoneRowBinding;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneViewHolder> {
    private LayoutInflater inflater;
    private List<Phone> phoneList;

    private final OnPhoneClickListener listener;




    public PhoneListAdapter(Context context, OnPhoneClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.phoneList = null;
        this.listener = listener;
    }
    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new PhoneViewHolder(PhoneRowBinding.inflate(this.inflater));
    }

    @Override
    public  void onBindViewHolder(@NonNull PhoneViewHolder holder , int position){
        if(this.phoneList != null){
            Phone currentPhone = this.phoneList.get(position);
            holder.getBinding().manufacturerTv.setText(currentPhone.getManufacturer());
            holder.getBinding().modelTv.setText(currentPhone.getModel());
            holder.itemView.setOnClickListener(view -> { this.listener.editPhone(currentPhone);
            });
        }

    }

    @Override
    public int getItemCount() {
        return this.phoneList != null ? this.phoneList.size() : 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
        notifyDataSetChanged();
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public Phone getPhoneAtPosition(int i){
        return this.phoneList.get(i);
    }
}
