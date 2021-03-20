package udesc.pin3.User;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Transactional
    public void registerUser(UserDTO dto) {
        User user = new User(dto);
        user.persist();
    }

    public User getUserById(long id) {
        PanacheQuery<PanacheEntityBase> result = User.find("id", id);
        return result.firstResult();
    }

    public UserDTO login(UserDTO dto) {
        PanacheQuery<PanacheEntityBase> result = User.find("email", dto.getEmail());
        User user = result.firstResult();

        if (user != null && user.getPassword().equals(dto.getPassword())) {
            return new UserDTO(user.id, user.getEmail(), user.getName(), user.getBirthday(), user.getCredits());
        } else {
            return null;
        }
    }
}
