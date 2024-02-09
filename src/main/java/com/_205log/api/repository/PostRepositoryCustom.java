package com._205log.api.repository;

import com._205log.api.domain.Post;
import com._205log.api.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
