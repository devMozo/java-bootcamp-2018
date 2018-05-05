package Bootcamp2018.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
/**
 * Represents a day
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class Day {
	public enum Daytime {
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY,
		SUNDAY
	};
	private Daytime day;
	private Date hour;
}
