package Controlador;

import Modelo.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Empleado
    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    int idEmpleado;

    //Cliente
    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    int idClienteSeleccionado;

    //Producto
    Producto producto = new Producto();
    ProductoDAO productoDAO = new ProductoDAO();
    int idProductoSeleccionado;

    //Venta
    Venta venta = new Venta();
    List<Venta> listaVenta = new ArrayList<>();
    int item;
    int codigo;
    String descripcion;
    double precio;
    int cantidad;
    double subtotal;
    double totalPagar;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }

        if (menu.equals("Empleado")) {
            switch (accion) {
                case "Listar":
                    List lista = edao.listar();
                    request.setAttribute("empleadosXXX", lista);
                    break;

                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUsuario");
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    idEmpleado = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(idEmpleado);
                    request.setAttribute("empleadoEditar", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUsuario");
                    em.setDni(dni1);
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(idEmpleado);
                    edao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                case "Delete":
                    idEmpleado = Integer.parseInt(request.getParameter("id"));
                    edao.delete(idEmpleado);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }

        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List listaProductos = productoDAO.listar();
                    request.setAttribute("listadoProductos", listaProductos);
                    break;

                case "Agregar":
                    String nombres = request.getParameter("txtNombres");
                    double precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stock = Integer.parseInt(request.getParameter("txtStock"));
                    String estado = request.getParameter("txtEstado");
                    producto.setNombres(nombres);
                    producto.setPrecio(precio);
                    producto.setStock(stock);
                    producto.setEstado(estado);
                    productoDAO.agregar(producto);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    idProductoSeleccionado = Integer.parseInt(request.getParameter("id"));
                    producto = productoDAO.listarId(idProductoSeleccionado);
                    request.setAttribute("productoSeleccionado", producto);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String nombresActualizar = request.getParameter("txtNombres");
                    double precioActualizar = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stockActualizar = Integer.parseInt(request.getParameter("txtStock"));
                    String estadoActualizar = request.getParameter("txtEstado");
                    producto.setNombres(nombresActualizar);
                    producto.setPrecio(precioActualizar);
                    producto.setStock(stockActualizar);
                    producto.setEstado(estadoActualizar);
                    productoDAO.actualizar(producto);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    idProductoSeleccionado = Integer.parseInt(request.getParameter("id"));
                    productoDAO.delete(idProductoSeleccionado);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }

        if (menu.equals("Cliente")) {
            switch (accion) {
                case "Listar":
                    List listaClientes = clienteDAO.listar();
                    request.setAttribute("listadoClientes", listaClientes);
                    break;

                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nombres = request.getParameter("txtNombres");
                    String direccion = request.getParameter("txtDireccion");
                    String estado = request.getParameter("txtEstado");
                    cliente.setDni(dni);
                    cliente.setNombres(nombres);
                    cliente.setDireccion(direccion);
                    cliente.setEstado(estado);
                    clienteDAO.agregar(cliente);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    idClienteSeleccionado = Integer.parseInt(request.getParameter("id"));
                    cliente = clienteDAO.listarId(idClienteSeleccionado);
                    request.setAttribute("clienteSeleccionado", cliente);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String dniActualizar = request.getParameter("txtDni");
                    String nombresActualizar = request.getParameter("txtNombres");
                    String direccionActualizar = request.getParameter("txtDireccion");
                    String estadoActualizar = request.getParameter("txtEstado");
                    cliente.setDni(dniActualizar);
                    cliente.setNombres(nombresActualizar);
                    cliente.setDireccion(direccionActualizar);
                    cliente.setEstado(estadoActualizar);
                    clienteDAO.actualizar(cliente);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    idClienteSeleccionado = Integer.parseInt(request.getParameter("id"));
                    clienteDAO.delete(idClienteSeleccionado);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Cliente.jsp").forward(request, response);
        }
        if (menu.equals("NuevaVenta")) {
            switch (accion) {

                case "BuscarCliente":
                    String dniCliente = request.getParameter("codigoCliente");
                    Cliente clienteBuscado = clienteDAO.buscar(dniCliente);
                    request.setAttribute("clienteBuscado", clienteBuscado);
                    break;

                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("codigoProducto"));
                    producto = productoDAO.listarId(id);
                    request.setAttribute("productoBuscado", producto);
                    request.setAttribute("listaVenta", listaVenta);
                    break;
                case "Agregar":
                    totalPagar = 0;
                    item = item + 1;
                    codigo = producto.getIdProducto();
                    descripcion = request.getParameter("nombreProducto");
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cantidad = Integer.parseInt(request.getParameter("cantidad"));
                    subtotal = precio * cantidad;

                    venta = new Venta();
                    venta.setItem(item);
                    venta.setIdVenta(codigo);
                    venta.setDescripcionProducto(descripcion);
                    venta.setPrecio(precio);
                    venta.setCantidad(cantidad);
                    venta.setSubtotal(subtotal);
                    listaVenta.add(venta);

                    for (int i = 0; i < listaVenta.size(); i++) {
                        totalPagar = totalPagar + listaVenta.get(i).getSubtotal();
                    }
                    request.setAttribute("totalPagar", totalPagar);

                    request.setAttribute("listaVenta", listaVenta);
                    break;

                default:
                    break;
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
