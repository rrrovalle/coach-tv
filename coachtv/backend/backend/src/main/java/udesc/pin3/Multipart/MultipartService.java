package udesc.pin3.Multipart;

import udesc.pin3.Mentoring.Mentoring;
import udesc.pin3.Mentoring.MentoringService;
import udesc.pin3.User.User;
import udesc.pin3.User.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@ApplicationScoped
public class MultipartService {

    @Inject
    UserService userService;
    @Inject
    MentoringService mentoringService;

    @Transactional
    public boolean setUserProfileImage(long userId, MultipartBody data) {
        try {
            byte[] fileBytes = data.file.readAllBytes();

            User user = userService.getUserById(userId);
            if (user == null)
                return false;

            user.setBytesProfileImage(fileBytes);
            user.setProfileImageFilename("profileImage" + data.fileName.substring(data.fileName.lastIndexOf(".")));
            user.persist();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MultipartBody downloadUserProfileImage(long userId) {
        User user = userService.getUserById(userId);

        MultipartBody body = new MultipartBody();
        body.fileName = user.getProfileImageFilename();
        body.file = new ByteArrayInputStream(user.getBytesProfileImage());
        return body;
    }

    @Transactional
    public boolean setMentoringPreview(long mentoringId, MultipartBody data) {
        try {
            byte[] fileBytes = data.file.readAllBytes();

            Mentoring mentoring = mentoringService.getMentoringById(mentoringId);
            if (mentoring == null)
                return false;

            mentoring.setPreviewImage(fileBytes);
            mentoring.setPreviewImageFilename("previewImage" + data.fileName.substring(data.fileName.lastIndexOf(".")));
            mentoring.persist();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MultipartBody downloadMentoringPreview(long mentoringId) {
        Mentoring mentoring = mentoringService.getMentoringById(mentoringId);

        MultipartBody body = new MultipartBody();
        body.fileName = mentoring.getPreviewImageFilename();
        body.file = new ByteArrayInputStream(mentoring.getPreviewImage());
        return body;
    }

    @Transactional
    public boolean setUserEncodedImage(long userId, String encodedImage) {
        User user = userService.getUserById(userId);
        if (user == null)
            return false;

        user.setEncodedImage(encodedImage);
        user.persist();
        return true;
    }
}
