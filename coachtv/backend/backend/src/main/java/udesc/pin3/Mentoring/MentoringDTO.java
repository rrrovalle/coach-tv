package udesc.pin3.Mentoring;

import udesc.pin3.User.User;
import udesc.pin3.User.UserDTO;

import javax.validation.constraints.NotBlank;

public class MentoringDTO {

    public MentoringDTO(Mentoring mentoring) {
        User user = mentoring.getCoach();
        this.coach = new UserDTO(user.id, user.getEmail(), user.getName(), user.getBirthday(), user.getCredits());
        this.id = mentoring.id;
        this.title = mentoring.getTitle();
        this.rating = mentoring.getRating();
        this.section = mentoring.getSection();
        this.description = mentoring.getDescription();
    }

    public MentoringDTO(long id, UserDTO coach, String title, String description, float rating, String section) {
        this.id = id;
        this.coach = coach;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.section = section;
    }

    public MentoringDTO(long id) {
        this.id = id;
    }

    public MentoringDTO() {

    }

    private long id;

    private UserDTO coach;

    @NotBlank(message = "O campo \"título\" deve ser preenchido. Tente novamente!")
    private String title;

    @NotBlank(message = "O campo \"descrição\" deve ser preenchido. Tente novamente!")
    private String description;

    private float rating;

    @NotBlank(message = "O campo \"seção\" deve ser preenchido. Tente novamente!")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
