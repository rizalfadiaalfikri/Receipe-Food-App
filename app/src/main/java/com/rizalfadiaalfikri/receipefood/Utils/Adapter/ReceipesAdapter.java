package com.rizalfadiaalfikri.receipefood.Utils.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Utils.Model.Receipes;
import com.rizalfadiaalfikri.receipefood.Utils.Model.ReceipesModel;

import java.util.ArrayList;

public class ReceipesAdapter extends RecyclerView.Adapter<ReceipesAdapter.MyViewHolder> {
    private ArrayList<ReceipesModel> receipesList = new ArrayList<>();
    private Context context;

    public ReceipesAdapter(ArrayList<ReceipesModel> receipesList, Context context) {
        this.receipesList = receipesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipes_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_receipes_title.setText(receipesList.get(position).getReceipes_name());

        Glide.with(context.getApplicationContext())
                .load(receipesList.get(position).getReceipes_images())
                .error(R.drawable.model_image)
                .into(holder.receipes_image);
    }

    @Override
    public int getItemCount() {
        return receipesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView receipes_image;
        private TextView txt_receipes_title, txt_receipes_duration;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            receipes_image = itemView.findViewById(R.id.receipes_image);
            txt_receipes_title = itemView.findViewById(R.id.txt_title_receipes);
            txt_receipes_duration = itemView.findViewById(R.id.txt_duration_receipes);

        }
    }
}
