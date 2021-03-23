package udesc.pin3.Mentoring;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import udesc.pin3.User.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

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
}
