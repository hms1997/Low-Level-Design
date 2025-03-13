package metadata;

import java.time.LocalDateTime;

public class Comment {
    private String description;
    private LocalDateTime dateTime;

    public Comment(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "description='" + description + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
