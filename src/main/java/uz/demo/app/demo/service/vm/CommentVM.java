package uz.demo.app.demo.service.vm;

import javax.validation.constraints.NotNull;

public class CommentVM {

    @NotNull
    private Long applicationId;

    @NotNull
    private String comment;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentVM{" +
                "applicationId=" + applicationId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
