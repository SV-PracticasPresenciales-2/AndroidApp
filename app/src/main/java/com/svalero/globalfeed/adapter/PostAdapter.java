package com.svalero.globalfeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.globalfeed.R;
import com.svalero.globalfeed.contract.post.DeletePostContract;
import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.presenter.post.DeletePostPresenter;
import com.svalero.globalfeed.view.UserDetailsView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.SuperheroHolder> implements DeletePostContract.View, Filterable {
    private Context context;
    private List<Post> postList;
    private Intent intentFrom;
    private DeletePostPresenter deletePostPresenter;
    private String token;
    //private View snackBarView;

    public PostAdapter(Context context, List<Post> postList, Intent intentFrom, String token) {
        this.context = context;
        this.postList = postList;
        this.intentFrom = intentFrom;
        this.deletePostPresenter = new DeletePostPresenter(this);
        this.token = token;
    }

    public Context getContext() {
        return context;
    }

    @NonNull
    public SuperheroHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);
        return new SuperheroHolder(view);
    }

    @Override
    public void onBindViewHolder(SuperheroHolder holder, int position) {
        holder.postMessage.setText(postList.get(position).getMessage());
        holder.postLikes.setText(postList.get(position).getLikes().toString() + " Likes!");
        holder.postUsername.setText(postList.get(position).getUserPost().getUsername());
        holder.postDate.setText(postList.get(position).getPostDate());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Filter getFilter() {
        return new PostFilter();
    }

    public class SuperheroHolder extends RecyclerView.ViewHolder {
        public TextView postMessage;
        public TextView postLikes;
        public TextView postUsername;
        public TextView postDate;

        //todo textViews


        public ImageButton postDelete;
        public View parentView;

        public SuperheroHolder(View view) {
            super(view);
            parentView = view;

            postMessage = view.findViewById(R.id.tvListPostMessage);
            postLikes = view.findViewById(R.id.tvListPostLikes);

            postUsername = view.findViewById(R.id.tvListPostUsername);

            postDate = view.findViewById(R.id.tvListPostDate);

            postDelete = view.findViewById(R.id.bListDelete);

            postUsername.setOnClickListener(v -> userDetails(getAdapterPosition()));
            postDelete.setOnClickListener(v -> deletePost(getAdapterPosition()));
        }
    }


    private void deletePost(int adapterPosition) {
        Post post = postList.get(adapterPosition);
        deletePostPresenter.deletePost(post.getId(), token);
        //TODO: Si no se borra correctamente, no borrar de la lista
        postList.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    private void userDetails(int adapterPosition) {
        Post post = postList.get(adapterPosition);
        Intent intent = new Intent(context, UserDetailsView.class);
        intent.putExtra("user", post.getUserPost());
        context.startActivity(intent);
    }

    class PostFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Post> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(postList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Post post : postList) {
                    if (post.getMessage().toLowerCase().contains(filterPattern)) {
                        filteredList.add(post);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            postList.clear();
            postList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    }
}
