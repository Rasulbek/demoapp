package uz.demo.app.demo.service.dto;

import uz.demo.app.demo.model.Application;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ApplicationDTO {
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Date createdDate;

    private Long authorId;

    private String authorName;

    public ApplicationDTO() {

    }

    public ApplicationDTO(Application application) {
        this.id = application.getId();
        this.title = application.getTitle();
        this.description = application.getDescription();
        this.createdDate = Date.from(application.getCreatedDate().toInstant());
        this.authorId = application.getAuthor().getId();
        this.authorName = application.getAuthor().getDisplayName();
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "ApplicationDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
