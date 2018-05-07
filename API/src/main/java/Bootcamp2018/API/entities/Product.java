package Bootcamp2018.API.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
	@NotNull
	@Column(name = "price")
	private Integer price;
	// The quantity of the products
	@Column(name = "cant")
	private int iCant = 0;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "product")
	private List<LineCart> arrLineCart = new ArrayList<>();
}
