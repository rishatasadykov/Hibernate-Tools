package entity;
// Generated 11.05.2016 13:42:22 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Orders generated by hbm2java
 */
@Entity
@Table(name = "orders", catalog = "itis_hib_db")
public class Orders implements java.io.Serializable {

	private Integer orderId;
	private String product;
	private int price;
	private int userId;

	public Orders() {
	}

	public Orders(String product, int price, int userId) {
		this.product = product;
		this.price = price;
		this.userId = userId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ORDER_ID", unique = true, nullable = false)
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name = "PRODUCT", nullable = false, length = 20)
	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Column(name = "PRICE", nullable = false)
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Column(name = "USER_ID", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
