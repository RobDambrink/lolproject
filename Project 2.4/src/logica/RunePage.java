package logica;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONObject;

public class RunePage implements Serializable {
	
	private static final long serialVersionUID = 34335489412165844L;
	
	public static final String ID="id";
	public static final String SLOT="slots";
	public static final String NAME="name";
	public static final String CURRENT="current";
	public static final String RUNEID="runeId";
	public static final String RUNESLOTID="runeSlotId";	
	
	private Long id;
	private Long[] slots = new Long[31];
	private String name;
	private boolean current=false;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the slots
	 */
	public Long[] getSlots() {
		return slots;
	}
	/**
	 * @param slots the slots to set
	 */
	public void setSlots(Long[] slots) {
		this.slots = slots;
	}
	
	/**
	 * 
	 * @param runeId This is the id of the rune
	 * @param runeSlotId this is the id where the rune is added to the slot
	 * see https://developer.riotgames.com/docs/game-constants
	 */
	public void addItemToSlot(Long runeId, Integer runeSlotId){
		slots[runeSlotId] = runeId;
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
	 * @return the current
	 */
	public boolean isCurrent() {
		return current;
	}
	/**
	 * @param current the current to set
	 */
	public void setCurrent(boolean current) {
		this.current = current;
	}
	
	public JSONObject getJSON(){
		JSONObject obj = new JSONObject();
		obj.put(ID, id);
		obj.put(CURRENT, current);
		obj.put(NAME, name);
		JSONArray ar = new JSONArray();
		for (int i = 0; i < slots.length; i++) {
			if (slots[i]!=null){
				JSONObject obj2 = new JSONObject();
				obj2.put(RUNEID, slots[i]);
				obj2.put(RUNESLOTID, i);
				ar.put(obj2);
			}
		}	
		obj.put("slots", ar);
		return obj;
	}
}
