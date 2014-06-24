package logica;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONObject;

public class MasteryPage implements Serializable{

	private static final long serialVersionUID = 4454229799179224691L;
	public static final String ID="id";
	public static final String MASTERIES="masteries";
	public static final String NAME="name";
	public static final String CURRENT="current";
	public static final String MASTERYID="id";
	public static final String MASTERYRANK="rank";
	
	private Long id;
	private HashMap<Integer,Integer> masteries = new HashMap<Integer,Integer>();
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
	 * @return the masteries
	 */
	public HashMap<Integer, Integer> getMasteries() {
		return masteries;
	}
	/**
	 * @param masteries the masteries to set
	 */
	public void setMasteries(HashMap<Integer,Integer> masteries) {
		this.masteries = masteries;
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
	public void addItemToMastery(int id, int rank) {
		masteries.put(id, rank);
	}
	
	public JSONObject getJSON(){
		JSONObject obj = new JSONObject();
		for(Entry<Integer, Integer> entry : masteries.entrySet()) {
			Integer id = entry.getKey();
			Integer rank = entry.getValue();
			JSONObject obj2 = new JSONObject();
			obj2.put(MASTERYID, id);
			obj2.put(MASTERYRANK, rank);
			obj.put(MASTERIES, obj2);
		}
		obj.put(ID, id);
		obj.put(NAME, name);
		obj.put(CURRENT, current);
		return obj;
	}
}
