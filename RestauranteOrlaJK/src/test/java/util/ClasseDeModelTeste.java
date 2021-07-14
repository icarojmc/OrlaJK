package util;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;

public class ClasseDeModelTeste implements Model {

	Map<String, Object> valores = new HashMap<>();
	
	@Override
	public Model addAttribute(String nome, Object valor) {
		valores.put(nome, valor);
		return this;
	}

	@Override
	public Model addAttribute(Object attributeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model addAllAttributes(Collection<?> attributeValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model addAllAttributes(Map<String, ?> attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model mergeAttributes(Map<String, ?> attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAttribute(String attributeName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getAttribute(String nome) {
		return valores.get(nome);
	}

	@Override
	public Map<String, Object> asMap() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
