package tech.overpass.conferauth.repository;


import tech.overpass.conferauth.model.db.Post;

import java.util.List;

public interface ApiPostRepository {
    List<Post> findPagedLatest(int offset, int limit);
}
