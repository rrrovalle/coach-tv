package udesc.pin3.Meeting;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.graalvm.collections.Pair;
import udesc.pin3.Mentoring.MentoringService;
import udesc.pin3.User.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MeetingService {

    @Inject
    UserService userService;
    @Inject
    MentoringService mentoringService;

    @Transactional
    public Pair<Integer, String> scheduleMeeting(long mentoringId, long userId, MeetingDTO dto) {
        Meeting meeting = new Meeting(dto);
        meeting.setCustomer(userService.getUserById(userId));

        if(meeting.getCustomer().getCredits() < meeting.getPrice()){
            return Pair.create(400, "You don't have enough credits to schedule this meeting. Please, " +
                    "check you credits amount and try again!");
        }

        if (mentoringService.getMentoringById(mentoringId) != null) {
            meeting.setMentoring(mentoringService.getMentoringById(mentoringId));
            meeting.persist();
            return Pair.create(200, "Meeting registered successfully!");
        } else {
            return Pair.create(400, "The mentoring provided is not valid. Try again!");
        }
    }

    public List<MeetingDTO> getMeetingsByUserId(long userId) {
        PanacheQuery<PanacheEntityBase> result = Meeting.find("customer_id", userId);
        List<Meeting> meetings = result.list();

        List<MeetingDTO> response = new ArrayList<>();
        meetings.forEach(m -> response.add(new MeetingDTO(m)));

        return response;
    }
}
