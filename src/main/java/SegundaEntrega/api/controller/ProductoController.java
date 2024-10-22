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
import utils.ApiResponse;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping(path = "/all")
    public ResponseEntity<?> getAllProductos() {
        try {
            List<ProductoDTO> productos = productoService.getAllProductos();
            return ResponseEntity.ok().body(new ApiResponse("Lista de productos", productos));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("NO HAY PRODUCTOS", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable("id") Long id){
        try {
            Optional<ProductoDTO> producto = productoService.getProductoById(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Producto no encontrado", e.getMessage()));
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
            return ResponseEntity.ok().body(new ApiResponse("Producto Eliminado", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Error: No se pudo eliminar el Producto", null));
        }
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        try {
            productoService.updateStockProducto(id, nuevoStock);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
