package SC403_tienda.controller;

import SC403_tienda.domain.Producto;
import SC403_tienda.service.CategoriaService;
import SC403_tienda.service.implement.FirebaseStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import SC403_tienda.service.ProductoService;

@Controller
@Slf4j
@RequestMapping("/producto") // Ruta del controlador
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalProductos", productos.size());
        return "/producto/listado";
    }

    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto) {
        return "/producto/modificar";
    }

    @PostMapping("/guardar")
    public String productoGuardar(Producto producto, @RequestParam("imagenFile") MultipartFile imagenFile) {
        // Guardar la categoría primero para asegurar que se genera un ID
        productoService.save(producto);
        if (!imagenFile.isEmpty()) {
            // Una vez guardado y con el ID, proceder a cargar la imagen
            String rutaImagen = firebaseStorageService.cargaImagen(imagenFile, "producto", producto.getIdProducto());
            producto.setRutaImagen(rutaImagen);
            // Guardar nuevamente con la ruta de la imagen
            productoService.save(producto);
        }
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(@PathVariable Long idProducto) {
        Producto producto = new Producto();
        producto.setIdProducto(idProducto);
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}") ///parametro Producto por Long idProducto
    public String productoModificar(@PathVariable Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);

        return "producto/modificar"; ///modifica se hizo refactor en template a modificar
    }
}
