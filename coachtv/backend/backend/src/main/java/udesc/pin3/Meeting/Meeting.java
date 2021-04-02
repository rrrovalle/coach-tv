package udesc.pin3.Meeting;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import udesc.pin3.Mentoring.Mentoring;
import udesc.pin3.User.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Meeting extends PanacheEntity {

    private int duration;

    private int price;

    private LocalDateTime startTime;

    public Meeting(MeetingDTO dto){
        this.duration = dto.getDuration();
        this.price = dto.getPrice();
        this.startTime = dto.getStartTime();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mentoring_id")
    private Mentoring mentoring;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private User customer;

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

    public Mentoring getMentoring() {
        return mentoring;
    }

    public void setMentoring(Mentoring mentoring) {
        this.mentoring = mentoring;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getCoach(){
        return mentoring.getCoach();
    }
}