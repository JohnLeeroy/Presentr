package com.johnli.presentr.network;

import com.johnli.presentr.model.QuestionPost;

/**
 * Created by johnli on 10/9/16.
 */
public class CreateQuestionRequest implements FirebaseRequest<QuestionPost> {

    QuestionPost questionPost;

    public CreateQuestionRequest(QuestionPost questionPost) {
        this.questionPost = questionPost;
    }

    @Override
    public String getPath() {
        return "/questions/" + questionPost.getRoomId() + "/" + questionPost.getId();
    }

    @Override
    public QuestionPost getData() {
        return questionPost;
    }
}
