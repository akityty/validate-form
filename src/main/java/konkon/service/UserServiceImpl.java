package konkon.service;

import konkon.model.User;
import konkon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;
  @Override
  public void save(User user) {
    userRepository.save(user);
  }
}
