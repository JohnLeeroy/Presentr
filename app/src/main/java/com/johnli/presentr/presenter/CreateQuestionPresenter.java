package com.johnli.presentr.presenter;

import com.johnli.presentr.fragment.CreateQuestionFragment;
import com.johnli.presentr.model.QuestionPost;
import com.johnli.presentr.network.CreateQuestionRequest;
import com.johnli.presentr.network.FirebaseRequestManager;

import java.util.UUID;

/**
 * Created by johnli on 10/9/16.
 */
public class CreateQuestionPresenter {

    CreateQuestionFragment view;
    public void attach(CreateQuestionFragment view) {
        this.view = view;
    }

    public void detach() {
        view = null;
    }

    public void createPost(String userId, String roomId, String question) {
        if(view != null) {
            QuestionPost post = new QuestionPost();
            post.setId(UUID.randomUUID().toString());
            post.setRoomId(roomId);
            post.setTitle(question);
            post.setUpVotes(0);
            post.setPosterId(userId);

            CreateQuestionRequest request = new CreateQuestionRequest(post);
            FirebaseRequestManager.getInstance().sendRequest(request);
        }
    }

}
