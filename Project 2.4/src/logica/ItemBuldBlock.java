package logica;

import java.io.Serializable;

public class ItemBuldBlock implements Serializable {
	private static final long serialVersionUID = 1158712010671706957L;
	private String name;
	private Long[] items;
	
	public ItemBuldBlock(String name,Long[] items){
		this.name=name;
		this.items=items;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the items
	 */
	public Long[] getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(Long[] items) {
		this.items = items;
	}
	
	
}
