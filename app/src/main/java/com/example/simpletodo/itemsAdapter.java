package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//responsible for displaying data from model
public class itemsAdapter extends RecyclerView.Adapter<itemsAdapter.ViewHolder> {

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;

    public itemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;

    }

    @NonNull
    @Override
    //responsible for creating each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create a new view;use layout to inflate to view
        View todoView = LayoutInflater.from(parent.getContext()). inflate(android.R.layout.simple_list_item_1, parent, false );

        //wrap it inside a View Holder an return it
        return new ViewHolder(todoView);
    }


     //responsible for binding
    @Override
    //responsible for taking data from the particular place and putting it in Viewholder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Grab the item at the position
        String item = items.get(position);
        //Bind the item into the specified view holder
        holder.bind(item);

    }

    @Override
    //number of items avaible in data
    public int getItemCount() {
        return items.size();

    }

    //Container to Provide easy access
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //Update the view  inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            //setOnclicklistern
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Notify the listener whch position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });

            }

        }

    }

