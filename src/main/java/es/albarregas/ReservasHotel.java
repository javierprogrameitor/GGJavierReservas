package es.albarregas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javier
 */
@WebServlet(name = "ReservasHotel", urlPatterns = {"/ReservasHotel"})
public class ReservasHotel extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registro de Hotel</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Has accedido sin pasar por el formulario <a href=\"" + request.getContextPath() + "\">Men&uacute;</a></h3>");
        out.println("</body>");
        out.println("</html>");

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
        response.setContentType("text/html;charset=UTF-8");

        String[] error = {"El nombre es obligatorio", "El apellido es obligatorio", "La telefono esta mal introducido", "Email incorrecto",
            "Numero no valido", "Fecha incorrecta"};
        Enumeration<String> parametros;

        String[] tp = {"simple", "doble", "matrimonio"};
        String[] forma = {"desayuno", "comida", "cena", "cama"};

        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Reservas de Hotel</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/style.css\"");
        out.println("</head>");
        out.println("<body>");
//////////////////////////////////////////////////////////////////////////////
/////////////Errores de Entrada de Datos por el Usuario//////////////////////

        boolean errores = false; // variable que me indicará si existe error

        int tipoError[] = new int[5];  // Array donde se almacenan los diferentes errores
        for (int i = 0; i < tipoError.length; i++) {
            tipoError[i] = -1;
        }

        //  int numero = Integer.parseInt(request.getParameter("numero"));
        parametros = request.getParameterNames();
        while (parametros.hasMoreElements()) {
            String nombre = parametros.nextElement();
            if (nombre.equals("nombre") && request.getParameter(nombre).length() == 0) {
                errores = true;
                tipoError[0] = 0;
            } else if (nombre.equals("apellidos") && request.getParameter(nombre).length() == 0) {
                errores = true;
                tipoError[1] = 1;
            } else if (nombre.equals("telefono") && request.getParameter(nombre).length() == 0
                    && validarTelefono(nombre) == true) {
                errores = true;
                tipoError[2] = 2;
            } else if (nombre.equals("email") && request.getParameter(nombre).length() == 0
                    && validarEmail(nombre) == true) {
                errores = true;
                tipoError[3] = 3;
            } else if (nombre.equals("numero") && request.getParameter(nombre).length() == 0) {

                if (validarEnteroPositivo(nombre) == true);
                errores = true;
                tipoError[4] = 4;
            } else if (nombre.equals("dia") && nombre.equals("mes") && validarFecha(nombre, nombre) == true
                    && request.getParameter(nombre).length() == 0) {
                errores = true;
                tipoError[5] = 5;
            }
        }

////////////////////////////////////////////////////////////////////////////////
/////////////////////////////Si no hay errores//////////////////////////////////
        if (!errores) {
            //----------------------------------------------------------1------------------------------------------------------------------------------
            out.println("<div>");
            out.println("<form action=\"ReservasHotel\" method=\"post\">");

            out.println("<div class=\"apartado1\">");
            out.println("     <fieldset>");
            out.println("        <legend>Registro Correcto</legend> ");
            out.println("        <br>");
            out.println("            <label>*Nombre</label>");
            out.print("            <input class=\"nombre\" type=\"text\" name=\"nombre\" value=\" " + request.getParameter("nombre") + "\">");
            out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");

            out.println("       <br>");
            out.println("           <label>*Apellidos</label>");
            out.print("            <input class=\"apellidos\" type=\"text\" name=\"apellidos\"  value=\" " + request.getParameter("apellidos") + "\">");

            out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");

            out.println("       <br>");
            out.println("           <label>*Tel&eacute;fono de contacto</label>");
            out.print("           <input class=\"telefono\" type=\"text\" name=\"telefono\"  value=\" " + request.getParameter("telefono") + "\">");

            out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");

            out.println("       <br>");
            out.println("            <label>*Email</label>");
            out.print("            <input class=\"email\" type=\"email\" name=\"email\"  value=\" " + request.getParameter("email") + "\">");

            out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");

            out.println("       <br>");
            out.println("   </fieldset>");
            out.println(" </div>");
            //-------------------------------------------------------------2-----------------------------------------------------------------------------
            out.println("<div class=\"apartado2\">");

            out.println("     <fieldset>");
            out.println("        <legend>*N&uacute;mero de noches</legend> ");
            int num = Integer.parseInt(request.getParameter("numero"));
            out.println("        <input class=\"numero\" type=\"number\" name=\"numero\" value=\" " + num + "\">");

            out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");

            out.println("     </fieldset>");
            out.println("</div>");
            //--------------------------------------------------------------3------------------------------------------------------------------------------
            out.println("<div class=\"apartado3\">");
            out.println("     <fieldset>");
            out.println("        <legend>*Fecha de Entrada</legend> ");
            out.println("         <label>D&iacute;a  :</label>");
            out.println("<select name=\"dia\">");
            int dia = (Integer.parseInt(request.getParameter("dia")));
            for (int i = 1; i < 32; i++) {
                String valor = (dia == i) ? "selected=\"selected\"" : "";
                out.println("<option value=\"" + i + "\"" + valor + ">" + i + "</option>");
            }
            out.println("</select>");
            out.println("         <label>Mes  :</label>");
            out.println("<select name=\"mes\">");
            int mes = (Integer.parseInt(request.getParameter("mes")));
            for (int i = 1; i < 13; i++) {
                String valor = (mes == i) ? "selected=\"selected\"" : "";
                out.println("<option value=\"" + i + "\"" + valor + ">" + i + "</option>");
            }
            out.println("</select>");
            out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");
            out.println("     </fieldset>");
            out.println("</div>");
            //---------------------------------------------------------------4-----------------------------------------------------------------------------------
            out.println("<div class=\"apartado4\">");

            out.println("     <fieldset>");
            out.println("        <legend>*Tipo de Habitacion</legend> ");
            //   StringBuilder sb = new StringBuilder("        <legend>*Tipo de Habitacion</legend> ");
            String[] prefes = request.getParameterValues("tipo");

            int indi = 0;
            for (int i = 0; i < tp.length; i++) {

                int encontrado = -1;
                if (prefes != null) {
                    for (String opcion : prefes) {
                        if (opcion.equals(tp[i])) {
                            encontrado = i;
                            break;
                        }
                    }
                }
                String valor = (encontrado != -1) ? "checked=\"checked\"" : "";
                out.print("<input type=\"radio\" name=\"tipo\"  value=\" " + valor + "\"> ");
                out.println(tp[indi]);
                // sb("<input type=\"radio\" name=\"tipo\" value=\"" + tipo[indi] + ">");
                // sb.append(valor);
                indi++;
            }
            //out.println(sb.toString());
            out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");
            out.println("     </fieldset>");
            out.println("</div>");

//--------------------------------------------------------------------------5------------------------------------------------------
            out.println("<div class=\"apartado5\">");

            out.println("     <fieldset>");
            out.println("        <legend>*Forma de Estancia</legend> ");
            String[] formato = request.getParameterValues("regimen");

            int index = 0;
            for (int i = 0; i < forma.length; i++) {

                int encontrado = -1;
                if (formato != null) {
                    for (String opcion : formato) {
                        if (opcion.equals(forma[i])) {
                            encontrado = i;
                            break;
                        }
                    }
                }
              //  String valor = (encontrado != -1);                           //? "checked=\"checked\"" : "";

                out.println("<input type=\"checkbox\" name=\"forma\" value=\" " + encontrado + "\">Desayuno");
                out.println(forma[index]);
                index++;
            }
            out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");
            out.println("</div>");
//------------------------------------------------------------------------------6--------------------------------------------------------       
            out.println("<div class=\"apartado6\">");

            out.println("     <fieldset>");
            out.println("        <legend id=\"comentario\">Comentarios</legend> ");
            out.println("<br>");
            out.println("<textarea name=\"comenta\" value=\" " + request.getParameter("comenta") + "\"rows=\"3\" cols=\"50\"></textarea>");
            out.println("<br>");
            out.println("     </fieldset>");

            out.println("</div>");
//-----------------------------------------------------------------------------Boton de Inicio-----------------------------------------------
            out.println("<div>");
            out.println("<input type=\"button\" name=\"limpiar\" value=\"Limpiar\" onClick=\"location.href='" + request.getContextPath() + "/HTML/reservaHotel.html';\"/>");
            out.println("</div>");

            out.println("</form>");
            out.println("</div>");

//////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////Errores/////////////////////////////////////////////
        } else {

            // Mostramos la capa de con los errores producidos
            out.println("<div id='errores'>");
            out.println("<h3>Problemas con el registro</h3>");
            out.println("</div>");

            for (int i = 0; i < tipoError.length; i++) {
                out.println("<p class=\"error\">");

                if (tipoError[i] != -1) {
                    out.println(error[tipoError[i]]);
                    out.println("</p>");
                }
            }

            out.println("<div>");
            out.println("<form action=\"ReservasHotel\" method=\"post\">");

            out.println("<div class=\"apartado1\">");
            out.println("     <fieldset>");
            out.println("        <legend>Datos Personales</legend> ");
            out.println("        <br>");
            out.println("            <label>*Nombre</label>");
            out.print("            <input class=\"nombre\" type=\"text\" name=\"nombre\" value=\" " + request.getParameter("nombre") + "\">");
            if (request.getParameter("nombre").length() == 0) {
                errores = true;
                out.println("<img src=\"IMG/CheckRojo.png\" alt=\"rojo\" class=\"icono-negativos\"/>");
            } else {
                out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");
            }

            out.println("       <br>");
            out.println("           <label>*Apellidos</label>");
            out.print("            <input class=\"apellidos\" type=\"text\" name=\"apellidos\"  value=\" " + request.getParameter("apellidos") + "\">");
            if (request.getParameter("apellidos").length() == 0) {
                errores = true;
                out.println("<img src=\"IMG/CheckRojo.png\" alt=\"rojo\" class=\"icono-negativos\"/>");
            } else {
                out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");
            }

            out.println("       <br>");
            out.println("           <label>*Tel&eacute;fono de contacto</label>");
            out.print("           <input class=\"telefono\" type=\"text\" name=\"telefono\"  value=\" " + request.getParameter("telefono") + "\">");
            if (request.getParameter("telefono").length() == 0) {
                errores = true;
                out.println("<img src=\"IMG/CheckRojo.png\" alt=\"rojo\" class=\"icono-negativos\"/>");
            } else {
                out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");
            }

            out.println("       <br>");
            out.println("            <label>*Email</label>");
            out.print("            <input class=\"email\" type=\"email\" name=\"email\"  value=\" " + request.getParameter("email") + "\">");
            if (request.getParameter("email").length() == 0) {
                errores = true;
                out.println("<img src=\"IMG/CheckRojo.png\" alt=\"rojo\" class=\"icono-negativos\"/>");
            } else {
                out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");
            }
            out.println("       <br>");
            out.println("   </fieldset>");
            out.println(" </div>");
////////////////////////////////////////////////////////////////Fin apartado 1//////////////////////////////////////////////////////////////////////////////           
            out.println("<div class=\"apartado2\">");

            out.println("     <fieldset>");
            out.println("        <legend>*N&uacute;mero de noches</legend> ");
            // int nume = Integer.parseInt(request.getParameter("numero"));
            out.println("        <input class=\"numero\" type=\"number\" name=\"numero\" value=\" " + request.getParameter("numero") + "\">");
            if (request.getParameter("numero").length() == 0) {
                errores = true;
                out.println("<img src=\"IMG/CheckRojo.png\" alt=\"rojo\" class=\"icono-negativos\"/>");
            } else {
                out.println("<img src=\"IMG/CheckVerde.png\" alt=\"rojo\" class=\"icono-positivo\"/>");
            }
            out.println("     </fieldset>");

            out.println("</div>");
/////////////////////////////////////////////////////////Fin apartado 2///////////////////////////////////////////////////////////////////////////////
            out.println("<div class=\"apartado3\">");

            out.println("     <fieldset>");
            out.println("        <legend>*Fecha de Entrada</legend> ");
            out.println("         <label>D&iacute;a  :</label>");

            out.println("<select name=\"dia\">");
            int dia = (Integer.parseInt(request.getParameter("dia")));
            for (int i = 1; i < 32; i++) {
                String valor = (dia == i) ? "selected=\"selected\"" : "";
                out.println("<option value=\"" + i + "\"" + valor + ">" + i + "</option>");
            }
            out.println("</select>");
            out.println("         <label>Mes  :</label>");
            out.println("<select name=\"mes\">");
            int mes = (Integer.parseInt(request.getParameter("mes")));
            for (int i = 1; i < 13; i++) {
                String valor = (mes == i) ? "selected=\"selected\"" : "";
                out.println("<option value=\"" + i + "\"" + valor + ">" + i + "</option>");
            }
            out.println("</select>");
            out.println("     </fieldset>");
            out.println("</div>");
/////////////////////////////////////////////////////////Fin apartado 3/////////////////////////////////////////////////////////////////////////////
            out.println("<div class=\"apartado4\">");

            out.println("     <fieldset>");
            out.println("        <legend>*Tipo de Habitacion</legend> ");
            //   StringBuilder sb = new StringBuilder("        <legend>*Tipo de Habitacion</legend> ");
            String[] prefes = request.getParameterValues("tipo");

            int indi = 0;
            for (int i = 0; i < tp.length; i++) {

                int encontrado = -1;
                if (prefes != null) {
                    for (String opcion : prefes) {
                        if (opcion.equals(tp[i])) {
                            encontrado = i;
                            break;
                        }
                    }
                }
                String valor = (encontrado != -1) ? "checked=\"checked\"" : "";
                out.print("<input type=\"radio\" name=\"tipo\"  value=\" " + valor + "\"> ");
                out.println(tp[indi]);
                // sb("<input type=\"radio\" name=\"tipo\" value=\"" + tipo[indi] + ">");
                // sb.append(valor);
                indi++;
            }
            //out.println(sb.toString());

            out.println("     </fieldset>");
            out.println("</div>");
/////////////////////////////////////////////////////Fin apartado 4//////////////////////////////////////////////////////////
            out.println("<div class=\"apartado5\">");

            out.println("     <fieldset>");
            out.println("        <legend>*Forma de Estancia</legend> ");
            String[] formato = request.getParameterValues("regimen");

            int index = 0;
            for (int i = 0; i < forma.length; i++) {

                int encontrado = -1;
                if (formato != null) {
                    for (String opcion : formato) {
                        if (opcion.equals(forma[i])) {
                            encontrado = i;
                            break;
                        }
                    }
                }
                String valor = (encontrado != -1) ? "checked=\"checked\"" : "";

                out.println("<input type=\"checkbox\" name=\"forma\" value=\" " + valor + "\">");
                out.println(forma[index]);
                index++;
            }

            out.println("</div>");
//////////////////////////////////////////////////Fin apartado 5////////////////////////////////////////////////////////////////////
            out.println("<div class=\"apartado6\">");

            out.println("     <fieldset>");
            out.println("        <legend id=\"comentario\">Comentarios</legend> ");
            out.println("<br>");
            out.println("<textarea name=\"comenta\" value=\" " + request.getParameter("comenta") + "\"rows=\"3\" cols=\"50\"></textarea>");
            out.println("<br>");
            out.println("     </fieldset>");

            out.println("</div>");
//////////////////////////////////////////////////Fin apartado 6//////////////////////////////////////////////////////////////////////
            out.println("<div>");
            out.println("<input type=\"submit\" name=\"enviar\" value=\"Enviar\" />");
            out.println("<input type=\"button\" name=\"limpiar\" value=\"Limpiar\" onClick=\"location.href='" + request.getContextPath() + "/HTML/reservaHotel.html';\"/>");
            out.println("</div>");

            out.println("</form>");
            out.println("</div>");
        }

    }

    private boolean validarEnteroPositivo(String numero) {

        return numero.matches("^[0-9]+$");
    }

    private boolean validarFecha(String dia, String mes) {
        String texto = dia + mes;
        return texto.matches("^(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])$");
    }

    private boolean validarTelefono(String telefono) {

        return telefono.matches("^[679]\\d{8}$");
    }

    private boolean validarEmail(String email) {

        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

}
