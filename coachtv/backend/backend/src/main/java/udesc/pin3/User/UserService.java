package udesc.pin3.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public void registerUser(UserDTO dto) {
        User user = new User(dto);
        user.persist();
    }

    public User getUserById(long id) {
        PanacheQuery<PanacheEntityBase> result = User.find("id", id);
        return result.firstResult();
    }
}
