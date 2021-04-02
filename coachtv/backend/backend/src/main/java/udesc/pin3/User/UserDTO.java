package udesc.pin3.User;

import udesc.pin3.Mentoring.Mentoring;
import udesc.pin3.Mentoring.MentoringDTO;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private long id;

    @NotBlank(message = "O campo \"e-mail\" deve ser preenchido!")
    private String email;

    @NotBlank(message = "O campo \"senha\" deve ser preenchido!")
    private String password;

    private String name;

    private LocalDate birthday;

    private int credits;

    private List<MentoringDTO> mentorings = new ArrayList<>();

    private String encodedImage;

    public UserDTO(String email, String password, String name, LocalDate birthday, int credits) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.credits = credits;
    }

    public UserDTO(long id, String email, String name, LocalDate birthday, int credits, String encodedImage) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.credits = credits;
        this.encodedImage = encodedImage;
    }

    public UserDTO(long id) {
        this.id = id;
    }

    public UserDTO(User user) {
        this.id = user.id;
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.birthday = user.getBirthday();
        this.credits = user.getCredits();
        this.encodedImage = user.getEncodedImage();

        for (Mentoring m : user.getMentorings()) {
            MentoringDTO mentoringDTO = new MentoringDTO(
                    new UserDTO(id), m.getTitle(), m.getDescription(), m.getRating(), m.getSection(), m.getEncodedPreviewImage());
            mentorings.add(mentoringDTO);
        }
    }

    public UserDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<MentoringDTO> getMentorings() {
        return mentorings;
    }

    public void setMentorings(List<MentoringDTO> mentorings) {
        this.mentorings = mentorings;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }
}
