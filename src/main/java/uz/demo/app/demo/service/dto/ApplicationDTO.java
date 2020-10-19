package uz.demo.app.demo.service.dto;

import uz.demo.app.demo.model.Application;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class ApplicationDTO {
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private ZonedDateTime createdDate;

    private String authorName;

    public ApplicationDTO() {

    }

    public ApplicationDTO(Application application) {
        this.id = application.getId();
        this.title = application.getTitle();
        this.description = application.getDescription();
        this.createdDate = application.getCreatedDate();
        String authorName = application.getAuthor().getFirstName() + " " + application.getAuthor().getLastName();
        this.authorName = authorName.length() > 1 ? authorName : application.getAuthor().getUsername();
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
