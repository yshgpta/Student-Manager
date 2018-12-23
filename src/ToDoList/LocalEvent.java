package ToDoList;

import java.time.LocalDate;

public class LocalEvent {
    private String description;
    private LocalDate date;

    public LocalEvent(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public String toString(){
        return "At "+date+ " Event to Perform: "+description;
    }
}
