package com.rizalfadiaalfikri.receipefood.Utils.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Screens.DetailedReceipeActivity;
import com.rizalfadiaalfikri.receipefood.Screens.Fragment.DetailedIngredientsFragment;
import com.rizalfadiaalfikri.receipefood.Utils.Model.Receipes;
import com.rizalfadiaalfikri.receipefood.Utils.Model.ReceipesModel;
import com.rizalfadiaalfikri.receipefood.Utils.Session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ReceipesAdapter extends RecyclerView.Adapter<ReceipesAdapter.MyViewHolder> {
    private List<ReceipesModel> receipesList = new ArrayList<>();
    private Context context;
    private Activity activity;
    SessionManager sessionManager;

    public ReceipesAdapter(List<ReceipesModel> receipesList, Context context) {
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
        holder.txt_receipes_title.setText(receipesList.get(position).getReceipe_name());
        holder.txt_receipes_duration.setText(receipesList.get(position).getReceipe_duration() + " Min");
        Toast.makeText(context, receipesList.get(position).getReceipe_name(), Toast.LENGTH_SHORT).show();

        Glide.with(context.getApplicationContext())
                .load(receipesList.get(position).getReceipe_images())
                .apply(new RequestOptions().centerInside().centerCrop())
                .error(R.drawable.model_image)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.receipes_image);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailedReceipeActivity.class);

                intent.putExtra("name", receipesList.get(position).getReceipe_name());
                intent.putExtra("ingredients", receipesList.get(position).getReceipe_ingredients());
                intent.putExtra("steps", receipesList.get(position).getReceipe_steps());

                int PRIVATE_MODE = 0;

                sessionManager = new SessionManager(context);
                sessionManager.createIngredients(receipesList.get(position).getReceipe_ingredients());

//                SharedPreferences sharedPref = activity.getSharedPreferences("myKey", PRIVATE_MODE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString("value", receipesList.get(position).getReceipe_ingredients());
//                editor.apply();

                context.startActivity(intent);
            }
        });
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
