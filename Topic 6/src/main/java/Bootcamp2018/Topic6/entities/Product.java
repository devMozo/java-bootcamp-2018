package Bootcamp2018.Topic6.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a product
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// The Product's Name
	@NotBlank
	@Column(name = "name")
	private String strName = new String();
	// The quantity of the products
	@Column(name = "cant")
	private int iCant = 0;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "product")
	private List<ShoppingCart> arrShoppingCart = new ArrayList<>();
}
