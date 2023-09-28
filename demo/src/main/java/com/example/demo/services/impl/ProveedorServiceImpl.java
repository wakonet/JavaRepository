package com.example.demo.services.impl;

import com.example.demo.POJO.Proveedor;
import com.example.demo.services.ProveedorService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String JSON_FILE = "proveedores.json";

    @Override
    public List<Proveedor> obtenerProveedores() throws IOException {
    	File file = new File(getClass().getClassLoader().getResource(JSON_FILE).getFile());
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Proveedor>>() {});
    }

    @Override
    public Proveedor obtenerProveedor(int id) throws IOException {
        List<Proveedor> proveedores = obtenerProveedores();
        return proveedores.stream()
                .filter(proveedor -> proveedor.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void agregarProveedor(Proveedor proveedor) throws IOException {
        List<Proveedor> proveedores = obtenerProveedores();
        proveedor.setId(generarNuevoId(proveedores));
        proveedores.add(proveedor);
        guardarProveedores(proveedores);
    }

    @Override
    public void actualizarProveedor(int id, Proveedor proveedorActualizado) throws IOException {
        List<Proveedor> proveedores = obtenerProveedores();
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getId() == id) {
                proveedores.set(i, proveedorActualizado);
                guardarProveedores(proveedores);
                return;
            }
        }
    }

    @Override
    public void eliminarProveedor(int id) throws IOException {
        List<Proveedor> proveedores = obtenerProveedores();
        proveedores.removeIf(proveedor -> proveedor.getId() == id);
        guardarProveedores(proveedores);
    }

    private void guardarProveedores(List<Proveedor> proveedores) throws IOException {
        objectMapper.writeValue(new File(getClass().getClassLoader().getResource(JSON_FILE).getFile()), proveedores);
    }

    private int generarNuevoId(List<Proveedor> proveedores) {
        int maxId = 0;
        for (Proveedor proveedor : proveedores) {
            maxId = Math.max(maxId, proveedor.getId());
        }
        return maxId + 1;
    }
}
