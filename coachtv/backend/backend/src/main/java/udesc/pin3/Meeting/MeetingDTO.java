package udesc.pin3.Meeting;

import udesc.pin3.Mentoring.MentoringDTO;
import udesc.pin3.User.UserDTO;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class MeetingDTO {

    private long id;

    @NotBlank(message = "To schedule your meeting, the field \"duration\" cannot be empty.")
    private int duration;

    private int price;

    @NotBlank(message = "To schedule your meeting, the meeting start time should be provided.")
    private LocalDateTime startTime;

    @NotBlank(message = "To schedule your meeting, the customer identification should be provided.")
    private UserDTO customer;

    @NotBlank(message = "To schedule your meeting, the mentoring identification should be provided.")
    private MentoringDTO mentoring;

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
}
