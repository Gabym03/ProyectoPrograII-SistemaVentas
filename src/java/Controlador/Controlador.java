/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
//Cambios importados
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.ProductoDAO;
//.
import java.io.IOException;
import java.io.PrintWriter;
//Cambios importados
import java.util.List;
//.
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {
    
    Empleado em=new Empleado();
    EmpleadoDAO edao=new EmpleadoDAO();
    Cliente c = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    Producto p = new Producto();
    ProductoDAO pdao = new ProductoDAO();
    int ide;
    int idc;
    int idp;
    v = new Venta();
    Venta v = new Venta();
    List<Venta>lista = new Arraylist<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu=request.getParameter("menu");
        String accion=request.getParameter("accion");
        if (menu.equals("Principal")){
            request.getRequestDispatcher("principal.jsp").forward(request, response);
        }
        if (menu.equals("Empleado")){
            switch (accion){
                //Corrección case "":
                case "Listar":
                    List lista=edao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    String dni=request.getParameter("txtDni");
                    String nom=request.getParameter("txtNombres");
                    String tel=request.getParameter("txtTel");
                    String est=request.getParameter("txtEstado");
                    String user=request.getParameter("txtUser");
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide=Integer.parseInt(request.getParameter("id"));
                    Empleado e=edao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1=request.getParameter("txtDni");
                    String nom1=request.getParameter("txtNombres");
                    String tel1=request.getParameter("txtTel");
                    String est1=request.getParameter("txtEstado");
                    String user1=request.getParameter("txtUser");
                    em.setDni(dni1);
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);
                    edao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide=Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        if (menu.equals("Clientes")){
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if (menu.equals("Productos")){
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        if (menu.equals("NuevaVenta")){
            switch (accion) {
                case "BuscarCliente":
                    String dni=request.getParameter("codigocliente");
                    c.setDni(dni);
                    c=cdao.buscar(dni);
                    request.setAttribute("c", c);
                    break;
                case "BuscarProducto":
                    int id=Integer.parseInt(request.getParameter("codigoProducto"));
                    p = pdao.listarId(id);
                    request.setAttribute("producto", p);
                    request.getAttribute("lista", lista);
                    break;
                case "Agregar": 
                    totalPagar = 0.0;
                    item = item +1;
                    cod = p.getId();
                    descripcion = request.getParameter("nomproducto");
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cant = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio * cant; 
                    v.setItem(item);
                    v.setId(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);
                    lista.add(v);
                    for (int i = 0; i < lista.size(); i++){
                        totalPagar=totalPagar + lista.get(i).getSubtotal();
                    }
                    request.getAttribute("totalpagar", totalPagar);
                    request.getAttribute("lista", lista);
                    break;
                default:
                    throw new AssertionError();
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
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static class Producto {

        public Producto() {
        }
    }

}
