package uz.demo.app.demo.model;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "main_application")
public class Application {

//  заголовок, текст заявки, дата создания.

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_date", nullable = false)
    private ZonedDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    public Application() {
    }

    public Application(Long id, String title, String description, ZonedDateTime createdDate, User author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author.getUsername() +
                ", createdDate=" + createdDate +
                '}';
    }
}
