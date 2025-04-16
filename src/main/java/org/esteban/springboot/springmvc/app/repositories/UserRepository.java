package org.esteban.springboot.springmvc.app.repositories;

import org.esteban.springboot.springmvc.app.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {


}
