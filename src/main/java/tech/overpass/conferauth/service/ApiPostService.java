package tech.overpass.conferauth.service;


import tech.overpass.conferauth.model.db.Post;
import tech.overpass.conferauth.model.other.PostTree;

import java.util.List;

public interface ApiPostService {
    List<Post> findPagedLatest(int offset, int limit);

    List<Post> findMostPopular(long limit);

    PostTree findById(long id);

    Post create(Post post);
}
