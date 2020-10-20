package uz.demo.app.demo.model;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "main_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "created_date", nullable = false)
    private ZonedDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    public Comment() {
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

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", author=" + author +
                ", application=" + application +
                '}';
    }
}
