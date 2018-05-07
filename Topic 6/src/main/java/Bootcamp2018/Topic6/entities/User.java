package Bootcamp2018.Topic6.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an User's Entity
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Column(name = "first_name", unique = true)
	private String firstName;
	@NotBlank
	@Column(name = "last_name", unique = true)
	private String lastName;
	@NotBlank
	@Column(name = "nickname", unique = true)
	private String nickname;
	@NotBlank
	private String password;
	@NotBlank
	@Column(name = "email", unique = true)
	private String email;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private List<LineCart> arrLineCart = new ArrayList<>();
}
