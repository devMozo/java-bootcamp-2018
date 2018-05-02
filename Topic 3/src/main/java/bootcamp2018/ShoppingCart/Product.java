package bootcamp2018.ShoppingCart;
/**
 * Represents a product
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class Product {
	// The Product's Name
	private String strName = new String();
	// The quantity of the products
	private int iCant = 0;
	/**
	 * Set the name of the product
	 * @param name
	 */
	public void setStrName(String name) {
		this.strName = name;
	}
	/**
	 * Set the quantity of the products
	 * @param cant
	 */
	public void setiCant(int cant) {
		this.iCant = cant;
	}
	/**
	 * Get the quantity of the products
	 * @return int
	 */
	public int getiCant() {
		return iCant;
	}

	/**
	 * Get the name of the product
	 * @return A String
	 */
	public String getStrName() {
		return strName;
	}
}
