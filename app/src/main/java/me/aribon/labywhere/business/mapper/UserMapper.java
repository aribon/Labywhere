package me.aribon.labywhere.business.mapper;

import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.ui.model.UserParcelable;

/**
 * Created by anthony.ribon
 * On 16/08/2017
 */

public class UserMapper implements Mapper<UserParcelable, User> {

  @Override
  public UserParcelable toView(User entity) {
    UserParcelable userParcelable = null;

    if (entity != null) {
      userParcelable = new UserParcelable();
      userParcelable.setEmail(entity.getEmail());
      userParcelable.setLogin(entity.getLogin());
    }

    return userParcelable;
  }

  @Override
  public User toModel(UserParcelable entity) {
    User user = null;

    if (entity != null) {
      user = new User();
      user.withEmail(entity.getEmail());
      user.withLogin(entity.getLogin());
    }

    return user;
  }
}
