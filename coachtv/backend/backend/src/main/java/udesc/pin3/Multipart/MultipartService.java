package udesc.pin3.Multipart;

import udesc.pin3.User.User;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
public class MultipartService {
    public void setUserProfileImage(MultipartBody data) {
        try {
            byte[] fileBytes = data.file.readAllBytes();
            String file = new String(fileBytes);

            User.find("id")

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
