package com.beeva.main.data.dao;

import java.util.Optional;

public interface DataAccessObject<T> {
	public Optional<T> buscarPorNombre(String nombre);
	public Optional<T> buscar(int id);
	public Optional<T> guardar(T record);
	public Optional<T> actualizar(T record);
	public boolean eliminar(int id);
}
