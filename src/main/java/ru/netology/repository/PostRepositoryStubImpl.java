package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryStubImpl implements PostRepository {
    private static List<Post> posts = List.of(new Post(10, "fsdf"), new Post(56, "vvvv"));
    private static long id;

    public synchronized List<Post> all() {
        return posts;
    }

    public synchronized Optional<Post> getById(long id) {
        return Optional.of(posts.get((int)id));
    }

    public synchronized Post save(Post post) {
        if (post.getId() == 0) {
            long newId = id++;
            post.setId(newId);
            posts.set((int) newId, post);
        }
        if (post.getId() != 0) {
            try {
                post = posts.get((int) post.getId());
            } catch (Exception e) {
                post.setId(0);
                return save(post);
            }
        }
        return post;
    }

    public synchronized void removeById(long id) {
        posts.set((int)id, null);
    }
}
