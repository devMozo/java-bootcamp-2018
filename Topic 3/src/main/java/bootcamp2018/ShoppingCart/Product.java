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
	public void setStrName(String name) throws Exception {
		// if the name is null or empty
		if(name == null || name.equals("")){
			throw new Exception("The name mustn't be null or empty");
		// If not..
		} else {
			// Set the name
			this.strName = name;
		}
	}
	/**
	 * Set the quantity of the products
	 * @param cant
	 */
	public void setiCant(int cant) throws Exception {
		// If the cant is zero
		if(cant == 0){
			throw new Exception("You putted a 0 in the Product's Setter");
		// If not..
		} else {
			// Set the correct quantity
			this.iCant = cant;
		}
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
