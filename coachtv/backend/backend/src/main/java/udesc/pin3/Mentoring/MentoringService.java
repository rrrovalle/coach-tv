package udesc.pin3.Mentoring;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import udesc.pin3.User.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MentoringService {

    @Transactional
    public void registerMentoring(MentoringDTO mentoringDTO) {
        Mentoring mentoring = new Mentoring(mentoringDTO);
        PanacheQuery<PanacheEntityBase> result = User.find("id", mentoringDTO.getCoach().getId());
        User user = result.firstResult();

        mentoring.setCoach(user);
        mentoring.persist();
    }

    public List<MentoringDTO> getAllMentorings() {
        List<Mentoring> mentorings = Mentoring.listAll();
        List<MentoringDTO> result = new ArrayList<>();
        mentorings.forEach(m -> result.add(new MentoringDTO(m)));
        return result;
    }

    public MentoringDTO getMentoringDTOById(long id) {
        Mentoring mentoring = getMentoringById(id);

        if (mentoring == null)
            return null;
        return new MentoringDTO(mentoring);
    }

    public Mentoring getMentoringById(long id) {
        PanacheQuery<PanacheEntityBase> result = Mentoring.find("id", id);
        return result.firstResult();
    }

    public List<String> getSectins() {
        return MentoringSections.getSections();
    }
}
