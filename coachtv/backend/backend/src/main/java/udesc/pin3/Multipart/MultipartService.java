package udesc.pin3.Multipart;

import udesc.pin3.User.User;
import udesc.pin3.User.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class MultipartService {

    @Inject
    UserService userService;

    public boolean setUserProfileImage(MultipartBody data) {
        try {
            byte[] fileBytes = data.file.readAllBytes();
            String file = new String(fileBytes);

            User user = userService.getUserById(data.userId);
            if (user == null)
                return false;

            user.setProfileImage(file);
            user.setProfileImageExtension(data.fileName.split("\\.")[1]);
            user.persist();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
