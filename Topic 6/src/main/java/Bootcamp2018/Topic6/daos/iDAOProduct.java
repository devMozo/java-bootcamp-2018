package Bootcamp2018.Topic6.daos;

import Bootcamp2018.Topic6.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents the Product's DAO
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface iDAOProduct extends JpaRepository<Product, Long> {
}
