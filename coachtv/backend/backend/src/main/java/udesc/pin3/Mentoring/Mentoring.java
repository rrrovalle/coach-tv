package udesc.pin3.Mentoring;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import udesc.pin3.User.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mentoring extends PanacheEntity {

    public Mentoring(MentoringDTO mentoring){
        this.coach = new User(mentoring.getCoach());
        this.title = mentoring.getTitle();
        this.rating = mentoring.getRating();
        this.section = mentoring.getSection();
        this.description = mentoring.getDescription();
    }

    public Mentoring() {
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coach_id")
    private User coach;

    private String title;

    private String description;

    private String section;

    private float rating;

    public User getCoach() {
        return coach;
    }

    public void setCoach(User coach) {
        this.coach = coach;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
