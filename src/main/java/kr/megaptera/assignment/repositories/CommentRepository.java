package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository {

    private final List<Comment> comments;

    public CommentRepository() {
        this.comments = new ArrayList();
    }

    public List<Comment> findAll(PostId postId) {
        List<Comment> cmt = new ArrayList();

        comments.stream()
                .filter(i -> i.postId().equals(postId))
                .forEach(j -> cmt.add(j));


        return cmt;
    }

    public Comment find(CommentId id) {

        Comment comment = comments.stream()
                .filter(cmt -> cmt.id().equals(id))
                .findFirst()
                .orElseThrow(PostNotFound::new);
        if (comment == null) {
            throw new PostNotFound();
        }

        return comment;
    }

    public void save(Comment comment) {
        comments.add(comment);
    }

    public void delete(CommentId id) {
        Comment comment = find(id);
        comments.remove(comment);
    }

}