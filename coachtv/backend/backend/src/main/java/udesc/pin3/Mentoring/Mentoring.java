package udesc.pin3.Mentoring;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import udesc.pin3.User.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mentoring extends PanacheEntity {

    public Mentoring(MentoringDTO mentoring){
        this.coach = mentoring.getCoach();
        this.evaluation = mentoring.getEvaluation();
        this.description = mentoring.getDescription();
    }

    public Mentoring() {
    }

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private User coach;

    private String description;

    private float evaluation;

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

    public float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }
}
