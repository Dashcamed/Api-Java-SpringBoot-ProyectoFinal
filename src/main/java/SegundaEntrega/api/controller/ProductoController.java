package SegundaEntrega.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SegundaEntrega.api.DTO.ProductoDTO;
import SegundaEntrega.api.services.ProductoService;
import utils.ApiResponseMsg;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping(path = "/all")
    public ResponseEntity<?> getAllProductos() {
        try {
            List<ProductoDTO> productos = productoService.getAllProductos();
            return ResponseEntity.ok().body(new ApiResponseMsg("Lista de productos", productos));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("NO HAY PRODUCTOS", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable("id") Long id){
        try {
            Optional<ProductoDTO> producto = productoService.getProductoById(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Producto no encontrado", e.getMessage()));
        }
    }

    @PostMapping("/createProduct")
    public ResponseEntity<ProductoDTO> createProduct(@RequestBody ProductoDTO productoDTO){
        ProductoDTO createdProduct = productoService.saveProducto(productoDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Producto Eliminado", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error: No se pudo eliminar el Producto", null));
        }
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        try {
            productoService.updateStockProducto(id, nuevoStock);
            return ResponseEntity.ok().body(new ApiResponseMsg("Stock Actualizado", id));
        } catch (IllegalArgumentException e) {
            // Mensaje específico para errores de stock negativo
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            // Error genérico en caso de que el producto no sea encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con id: " + id);
        }
    }
}
