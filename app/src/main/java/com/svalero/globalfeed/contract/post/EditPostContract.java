package com.svalero.globalfeed.contract.post;

import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.domain.dto.PostDTO;

public interface EditPostContract {
    interface Model {
        interface OnEditPostListener {
            void onEditPostSuccess(Post post);

            void onEditPostError(String message);
        }

        void editPost(String token, long postid, PostDTO post, OnEditPostListener listener);
    }

    interface View {
        void showError(String message);

        void showMessage(String message);
    }

    interface Presenter {
          void editPost(long postId, PostDTO post, String token);
    }

}
