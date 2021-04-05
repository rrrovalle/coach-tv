package udesc.pin3.Meeting;

import udesc.pin3.Mentoring.Mentoring;
import udesc.pin3.Mentoring.MentoringDTO;
import udesc.pin3.User.User;
import udesc.pin3.User.UserDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MeetingDTO {

    private long id;

    @NotNull(message = "To schedule your meeting, the field \"duration\" cannot be empty.")
    private int duration;

    private int price;

    //    @NotNull(message = "To schedule your meeting, the meeting start time should be provided.")
    private LocalDateTime startTime;

    private UserDTO customer;

    private MentoringDTO mentoring;

    private String description;

    public MeetingDTO(int duration, int price, LocalDateTime startTime, UserDTO customer, MentoringDTO mentoring, String description) {
        this.duration = duration;
        this.price = price;
        this.startTime = startTime;
        this.customer = customer;
        this.mentoring = mentoring;
        this.description = description;
    }

    public MeetingDTO(Meeting meeting) {
        this.id = meeting.id;
        this.duration = meeting.getDuration();
        this.price = meeting.getPrice();
        this.startTime = meeting.getStartTime();
        this.description = meeting.getDescription();

        User user = meeting.getCustomer();
        this.customer = new UserDTO(user.id, user.getEmail(), user.getName(), user.getBirthday(), user.getCredits());

        Mentoring m = meeting.getMentoring();
        mentoring = new MentoringDTO(m.id, new UserDTO(id), m.getTitle(), m.getDescription(), m.getRating(), m.getSection());
    }

    public MeetingDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public UserDTO getCustomer() {
        return customer;
    }

    public void setCustomer(UserDTO customer) {
        this.customer = customer;
    }

    public MentoringDTO getMentoring() {
        return mentoring;
    }

    public void setMentoring(MentoringDTO mentoring) {
        this.mentoring = mentoring;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
