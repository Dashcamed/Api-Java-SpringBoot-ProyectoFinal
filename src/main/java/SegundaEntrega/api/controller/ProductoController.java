package SegundaEntrega.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SegundaEntrega.api.DTO.ProductoDTO;
import SegundaEntrega.api.model.Panaderia;
import SegundaEntrega.api.model.Producto;
import SegundaEntrega.api.services.PanaderiaService;
import SegundaEntrega.api.services.ProductoService;
import utils.ApiResponse;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Long id) {
        Optional<Producto> productoOpt = productoService.getProductoById(id);
        return productoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody Producto producto) {
        this.productoService.saveProducto(producto);
        return ResponseEntity.ok().body(new ApiResponse("Producto Creado", producto));
    }

    @Autowired
    private PanaderiaService panaderiaService;
    // Actualizar un producto (puedes modificarlo según sea necesario)
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        Optional<Producto> existingProductoOpt = productoService.getProductoById(id);
        if (existingProductoOpt.isPresent()) {
            Producto existingProducto = existingProductoOpt.get();

            // Asignamos los valores del DTO al producto existente
            existingProducto.setNombre(productoDTO.getNombre());
            existingProducto.setPrecio(productoDTO.getPrecio());
            existingProducto.setStock(productoDTO.getStock());
            existingProducto.setCategoria(productoDTO.getCategoria());

            // Buscar la panadería por ID y asignarla al producto
            Optional<Panaderia> panaderiaOpt = panaderiaService.getPanaderiaById(productoDTO.getPanaderiaId());
            if (panaderiaOpt.isPresent()) {
                existingProducto.setPanaderia(panaderiaOpt.get());
            } else {
                return ResponseEntity.badRequest().build(); // Error si la panadería no existe
            }

            // Guardamos el producto actualizado
            Producto updatedProducto = productoService.saveProducto(existingProducto);
            return ResponseEntity.ok(updatedProducto);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (productoService.getProductoById(id).isPresent()) {
            productoService.deleteProducto(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // Modificar el stock de un producto
    @PatchMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        try {
            productoService.updateStockProducto(id, nuevoStock);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
