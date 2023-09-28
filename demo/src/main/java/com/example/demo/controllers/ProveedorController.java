package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.POJO.Proveedor;
import com.example.demo.services.ProveedorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/proveedores")
@Api(value = "ProveedorController", description = "Operaciones relacionadas con los proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @ApiOperation(value = "Obtener todos los proveedores", response = Proveedor.class, responseContainer = "List")
    public List<Proveedor> obtenerProveedores() throws IOException {
        return proveedorService.obtenerProveedores();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un proveedor por su ID", response = Proveedor.class)
    public Proveedor obtenerProveedor(
            @ApiParam(value = "ID del proveedor a obtener", required = true) @PathVariable int id) throws IOException {
        return proveedorService.obtenerProveedor(id);
    }

    @PostMapping
    @ApiOperation(value = "Agregar un nuevo proveedor")
    public void agregarProveedor(
            @ApiParam(value = "Datos del nuevo proveedor", required = true) @RequestBody Proveedor proveedor) throws IOException {
        proveedorService.agregarProveedor(proveedor);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar un proveedor por su ID")
    public void actualizarProveedor(
            @ApiParam(value = "ID del proveedor a actualizar", required = true) @PathVariable int id,
            @ApiParam(value = "Datos actualizados del proveedor", required = true) @RequestBody Proveedor proveedor) throws IOException {
        proveedorService.actualizarProveedor(id, proveedor);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar un proveedor por su ID")
    public void eliminarProveedor(
            @ApiParam(value = "ID del proveedor a eliminar", required = true) @PathVariable int id) throws IOException {
        proveedorService.eliminarProveedor(id);
    }
}

