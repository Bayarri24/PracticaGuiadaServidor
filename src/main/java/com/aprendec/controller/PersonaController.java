package com.aprendec.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aprendec.dao.PersonaDAO;
import com.aprendec.model.Persona;

/**
 * Servlet implementation class PersonaController
 */
@WebServlet(description = "administra peticiones para la tabla personas", urlPatterns = { "/personas" })
public class PersonaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonaController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        String opcion = request.getParameter("opcion");

        if (opcion.equals("crear")) {
            System.out.println("Usted ha presionado la opcion crear");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
            requestDispatcher.forward(request, response);
        } else if (opcion.equals("listar")) {

            PersonaDAO personaDAO = new PersonaDAO();
            List<Persona> lista = new ArrayList<>();
            try {
                lista = personaDAO.obtenerPersonas();
                for (Persona persona : lista) {
                    System.out.println(persona);
                }

                request.setAttribute("lista", lista);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
                requestDispatcher.forward(request, response);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("Usted ha presionado la opcion listar");
        } else if (opcion.equals("meditar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("Editar id: " + id);
            PersonaDAO personaDAO = new PersonaDAO();
            Persona p = new Persona();
            try {
                p = personaDAO.obtenerPersona(id);
                System.out.println(p);
                request.setAttribute("persona", p);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
                requestDispatcher.forward(request, response);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (opcion.equals("eliminar")) {
            PersonaDAO personaDAO = new PersonaDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                personaDAO.eliminar(id);
                System.out.println("Registro eliminado satisfactoriamente...");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String opcion = request.getParameter("opcion");
        Date fechaActual = new Date();

        if (opcion.equals("guardar")) {
            PersonaDAO personaDAO = new PersonaDAO();
            Persona persona = new Persona();
            persona.setNombre(request.getParameter("nombre"));
            persona.setApellidos(request.getParameter("apellidos"));
            persona.setEdad(Integer.parseInt(request.getParameter("edad")));
            persona.setEmail(request.getParameter("email"));
            persona.setFechaCrear(new java.sql.Date(fechaActual.getTime()));
            try {
                personaDAO.guardar(persona);
                System.out.println("Registro guardado satisfactoriamente...");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (opcion.equals("editar")) {
            Persona persona = new Persona();
            PersonaDAO personaDAO = new PersonaDAO();

            persona.setId(Integer.parseInt(request.getParameter("id")));
            persona.setNombre(request.getParameter("nombre"));
            persona.setApellidos(request.getParameter("apellidos"));
            persona.setEdad(Integer.parseInt(request.getParameter("edad")));
            persona.setEmail(request.getParameter("email"));
            persona.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));
            try {
                personaDAO.editar(persona);
                System.out.println("Registro editado satisfactoriamente...");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}