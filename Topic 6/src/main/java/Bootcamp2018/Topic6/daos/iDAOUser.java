package Bootcamp2018.Topic6.daos;

import Bootcamp2018.Topic6.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents the Shopping Cart's DAO
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface iDAOUser extends JpaRepository<User, Long>{}
