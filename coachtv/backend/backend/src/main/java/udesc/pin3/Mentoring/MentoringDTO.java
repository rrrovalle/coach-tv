package udesc.pin3.Mentoring;

import udesc.pin3.User.User;

public class MentoringDTO {

    public MentoringDTO(User coach, String description, float evaluation) {
        this.coach = coach;
        this.description = description;
        this.evaluation = evaluation;
    }

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
