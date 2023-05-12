package com.svalero.globalfeed.contract.post;

import com.svalero.globalfeed.domain.Post;

import java.util.List;

public interface PostListContract {
    interface Model{
        interface OnLoadPostListener {
            void onLoadPostsSuccess(List<Post> postList);
            void onLoadPostsError(String message);
        }
        void loadAllPosts(OnLoadPostListener listener);
    }

    interface View{
        void showPosts(List<Post> postList);
        void showMessage(String name);
    }

    interface Presenter {
        void loadAllPosts();
    }
}
