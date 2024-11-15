package SC403_tienda.controller;

import SC403_tienda.domain.Categoria;
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
import SC403_tienda.service.CategoriaService;

@Controller
@Slf4j
@RequestMapping("/categoria") // Ruta del controlador
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size());
        return "/categoria/listado";
    }

    @GetMapping("/nuevo")
    public String categoriaNuevo(Categoria categoria) {
        return "/categoria/modificar";
    }

    @PostMapping("/guardar")
    public String categoriaGuardar(Categoria categoria, @RequestParam("imagenFile") MultipartFile imagenFile) {
        // Guardar la categoría primero para asegurar que se genera un ID
        categoriaService.save(categoria);
        if (!imagenFile.isEmpty()) {
            // Una vez guardado y con el ID, proceder a cargar la imagen
            String rutaImagen = firebaseStorageService.cargaImagen(imagenFile, "categoria", categoria.getIdCategoria());
            categoria.setRutaImagen(rutaImagen);
            // Guardar nuevamente con la ruta de la imagen
            categoriaService.save(categoria);
        }
        return "redirect:/categoria/listado";
    }

    @GetMapping("/eliminar/{idCategoria}")
    public String categoriaEliminar(@PathVariable Long idCategoria) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        categoriaService.delete(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/modificar/{idCategoria}") ///parametro Categoria por Long
    public String categoriaModificar(@PathVariable Categoria idCategoria, Model model) {
        Categoria categoria = categoriaService.getCategoria(idCategoria);
        if (categoria == null) {
            return "redirect:/categoria/listado";
        }
        model.addAttribute("categoria", categoria);
        return "categoria/modificar"; ///modifica se hizo refactor en template a modificar
    }
}
