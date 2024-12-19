package SC403_tienda.service.impl;

import SC403_tienda.dao.FacturaDao;
import SC403_tienda.dao.ProductoDao;
import SC403_tienda.dao.UsuarioDao;
import SC403_tienda.dao.VentaDao;
import SC403_tienda.domain.Factura;
import SC403_tienda.domain.Item;
import SC403_tienda.domain.Usuario;
import SC403_tienda.domain.Venta;
import SC403_tienda.service.ItemService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private HttpSession session;

    @Override
    public List<Item> gets() {
        @SuppressWarnings("unchecked")
        List<Item> listaItems = (List<Item>) session.getAttribute("listaItems");
        return listaItems == null ? new ArrayList<>() : listaItems;
    }

    @Override
    public Item get(Item item) {
        List<Item> listaItems = gets();
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void delete(Item item) {
        List<Item> listaItems = gets();
        listaItems.removeIf(i -> Objects.equals(i.getIdProducto(), item.getIdProducto()));
        session.setAttribute("listaItems", listaItems);
    }

    @Override
    public void save(Item item) {
        List<Item> listaItems = gets();
        boolean exists = false;
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                exists = true;
                if (i.getCantidad() < i.getExistencias()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;
            }
        }
        if (!exists) {
            item.setCantidad(1);
            listaItems.add(item);
        }
        session.setAttribute("listaItems", listaItems);
    }

    @Override
    public void update(Item item) {
        List<Item> listaItems = gets();
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
        session.setAttribute("listaItems", listaItems);
    }

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private ProductoDao productoDao;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;

    @Override
    public void facturar() {
        String username = getAuthenticatedUsername();
        if (username.isBlank()) {
            System.out.println("username en blanco...");
            return;
        }

        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            System.out.println("Usuario no existe en usuarios...");
            return;
        }

        Factura factura = new Factura(usuario.getIdUsuario());
        factura = facturaDao.save(factura);

        List<Item> listaItems = gets();
        if (listaItems != null) {
            double total = 0;
            for (Item i : listaItems) {
                var producto = productoDao.getReferenceById(i.getIdProducto());
                if (producto.getExistencias() >= i.getCantidad()) {
                    Venta venta = new Venta(factura.getIdFactura(), i.getIdProducto(), i.getPrecio(), i.getCantidad());
                    ventaDao.save(venta);
                    producto.setExistencias(producto.getExistencias() - i.getCantidad());
                    productoDao.save(producto);
                    total += i.getCantidad() * i.getPrecio();
                }
            }

            factura.setTotal(total);
            facturaDao.save(factura);

            listaItems.clear();
            session.setAttribute("listaItems", listaItems);
        }
    }

    @Override
    public double getTotal() {
        List<Item> listaItems = gets();
        return listaItems.stream().mapToDouble(i -> i.getCantidad() * i.getPrecio()).sum();
    }

    private String getAuthenticatedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        } else if (principal != null) {
            return principal.toString();
        }
        return "";
    }
}