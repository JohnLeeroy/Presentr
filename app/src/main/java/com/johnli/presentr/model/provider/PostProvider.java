package com.johnli.presentr.model.provider;

import com.johnli.presentr.model.QuestionPost;

import java.util.List;

/**
 * Created by johnli on 9/29/16.
 */
public interface PostProvider {

    List<QuestionPost> getPosts();
}
