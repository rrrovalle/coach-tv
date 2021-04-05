package udesc.pin3.Meeting;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.graalvm.collections.Pair;
import udesc.pin3.Mentoring.MentoringService;
import udesc.pin3.User.User;
import udesc.pin3.User.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

            if(meeting.getCustomer() == meeting.getCoach()){
                return Pair.create(400, "You can't purchase your own mentoring. Please, select another mentoring!");
            }

            meeting.getCustomer().setCredits(meeting.getCustomer().getCredits() - meeting.getPrice());
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

    public List<MeetingDTO> getMeetingsByMentoringId(long mentoringId) {
        PanacheQuery<PanacheEntityBase> result = Meeting.find("mentoring_id", mentoringId);
        List<Meeting> meetings = result.list();

        List<MeetingDTO> response = new ArrayList<>();
        meetings.forEach(m -> response.add(new MeetingDTO(m)));

        return response;
    }

    public Meeting getMeetingById(long id){
        PanacheQuery<PanacheEntityBase> result = Meeting.find("id", id);
        return result.firstResult();
    }

    public Pair<Integer, Object> joinMeetingRoom(long meetingId, long userId) {
        Meeting meeting = getMeetingById(meetingId);
        User user = userService.getUserById(userId);

        if(user.id.equals(meeting.getCoach().id) || user.id.equals(meeting.getCustomer().id)){
            if(meeting.getStartTime().minusMinutes(5).isBefore(LocalDateTime.now())){
                // Join video-call room
                // Return IP of other call members
                return Pair.create(200, "This video-call room is not ready yet.");
            } else {
                return Pair.create(400, "This video-call room is not ready yet.");
            }
        } else {
            return Pair.create(400, "You are not authorized to join this meeting.");
        }
    }
}
