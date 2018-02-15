package com.beeva.main.data.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beeva.main.data.dao.h2Repository.CustomUserH2;
import com.beeva.main.model.User;

@Component
public class CustomUserRepository implements DataAccessObject<User>{

	@Autowired
	private CustomUserH2 customUserH2;
	
	public Optional<User> buscarPorNombre(String nombre){
		return Optional.ofNullable(customUserH2.findOneByUsername(nombre));
	}
	
	public Optional<User> buscar(int id) {
		return null;
	}

	public Optional<User> guardar(User record) {
		return null;
	}

	public Optional<User> actualizar(User record) {
		return null;
	}

	public boolean eliminar(int id) {
		return false;
	}

}
