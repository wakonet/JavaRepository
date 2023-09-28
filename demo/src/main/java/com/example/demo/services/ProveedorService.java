package com.example.demo.services;

import java.io.IOException;
import java.util.List;

import com.example.demo.POJO.Proveedor;

public interface ProveedorService {
	public List<Proveedor> obtenerProveedores() throws IOException;

	public Proveedor obtenerProveedor(int id) throws IOException;

	public void agregarProveedor(Proveedor proveedor) throws IOException;

	public void actualizarProveedor(int id, Proveedor proveedorActualizado) throws IOException;

	public void eliminarProveedor(int id) throws IOException;

}
