package udesc.pin3.Mentoring;

import udesc.pin3.User.User;
import udesc.pin3.User.UserDTO;

public class MentoringDTO {

    public MentoringDTO(Mentoring mentoring) {
        this.coach = new UserDTO(mentoring.getCoach());
        this.title = mentoring.getTitle();
        this.rating = mentoring.getRating();
        this.section = mentoring.getSection();
        this.description = mentoring.getDescription();
    }

    public MentoringDTO(UserDTO coach, String title, String description, float rating, String section) {
        this.coach = coach;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.section = section;
    }

    public MentoringDTO(){

    }

    private UserDTO coach;

    private String title;

    private String description;

    private float rating;

    private String section;

    public UserDTO getCoach() {
        return coach;
    }

    public void setCoach(UserDTO coach) {
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
