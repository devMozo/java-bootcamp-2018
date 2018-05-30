package Bootcamp2018.API.daos;

import Bootcamp2018.API.entities.Category;
import Bootcamp2018.API.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents the Category's DAO
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface iDAOCategory extends JpaRepository<Category, Long> {
	Category findByName(String name);
}
