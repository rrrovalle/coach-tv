package udesc.pin3.User;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import udesc.pin3.Mentoring.Mentoring;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Person")
public class User extends PanacheEntity {

    public User(UserDTO dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.birthday = dto.getBirthday();
        this.credits = dto.getCredits();
    }

    public User() {
    }

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String name;

    private LocalDate birthday;

    private int credits;

    @OneToMany(mappedBy = "coach")
    private List<Mentoring> mentorings = new ArrayList<>();

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

    public List<Mentoring> getMentorings() {
        return mentorings;
    }

    public void setMentorings(List<Mentoring> mentorings) {
        this.mentorings = mentorings;
    }
}
