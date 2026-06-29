package pl.pollub.lab2;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.pollub.lab2.databinding.PhoneRowBinding;

public class PhoneViewHolder extends RecyclerView.ViewHolder {
private final PhoneRowBinding binding;

    public PhoneViewHolder(@NonNull PhoneRowBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


    public PhoneRowBinding getBinding() {
        return binding;
    }

}
