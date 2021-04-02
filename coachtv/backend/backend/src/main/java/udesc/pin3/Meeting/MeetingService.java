package udesc.pin3.Meeting;

import org.graalvm.collections.Pair;
import udesc.pin3.Mentoring.MentoringService;
import udesc.pin3.User.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MeetingService {

    @Inject
    UserService userService;
    @Inject
    MentoringService mentoringService;

    public Pair<Integer, String> scheduleMeeting(MeetingDTO dto) {
        Meeting meeting = new Meeting(dto);
        meeting.setCustomer(userService.getUserById(dto.getCustomer().getId()));

        if(mentoringService.getMentoringById(dto.getMentoring().getId()) != null){
            meeting.setMentoring(mentoringService.getMentoringById(dto.getMentoring().getId()));
            return Pair.create(200, "Meeting registered successfully!");
        } else {
            return Pair.create(400, "The mentoring provided is not valid. Try again!");
        }
    }
}
