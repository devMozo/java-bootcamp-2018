package Bootcamp2018.API.daos;

import Bootcamp2018.API.entities.LineCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents the Shopping Cart's DAO
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface iDAOLineCart extends JpaRepository<LineCart, Long>{
}
