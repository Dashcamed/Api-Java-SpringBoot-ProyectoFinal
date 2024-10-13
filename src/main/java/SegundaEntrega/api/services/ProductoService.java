package SegundaEntrega.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SegundaEntrega.api.model.Producto;
import SegundaEntrega.api.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }
    //trae un porducto por id
    public Optional<Producto> getProductoById(Long id){
        return productoRepository.findById(id);
    }
    // guarda un producto
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    // borra un producto
    public void deleteProducto(Long id) {
        if(productoRepository.existsById(id)){
            productoRepository.deleteById(id);
        } else {
            System.out.println("el producto no existe");
        }
    }
    //modifica el stock de un producto
    public void updateStockProducto(Long productoId, int nuevoStock){

        Optional<Producto> productoOpt = productoRepository.findById(productoId);

        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            int stockActual = producto.getStock();
            producto.setStock(stockActual + nuevoStock);
            productoRepository.save(producto);
        } else {
            // Manejar el caso donde no se encontró el producto
            throw new RuntimeException("Producto no encontrado con id: " + productoId);
        }
    }
}
