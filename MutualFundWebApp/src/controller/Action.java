package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
	public abstract String getName();
	
	public abstract String perform(HttpServletRequest request);
	
	private static Map<String, Action> map = new HashMap<String, Action>();

	public static void add(Action action) {
		synchronized (map) {
			map.put(action.getName(), action);
		}
	}

	public static String perform(String actionName, HttpServletRequest request) {
		Action action;
		
		synchronized(map) {
			action = map.get(actionName);
		}
		
		if(action == null)
			return null;
		
		return action.perform(request);
	}
}
