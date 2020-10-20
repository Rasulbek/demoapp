package uz.demo.app.demo.service.dto;

import uz.demo.app.demo.model.Comment;

import java.util.Date;

public class CommentDTO {
    private Long id;

    private String comment;

    private String authorName;

    private Date createdDate;

    public CommentDTO() {

    }

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdDate = Date.from(comment.getCreatedDate().toInstant());
        this.authorName = comment.getAuthor().getDisplayName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", authorName='" + authorName + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
