/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.fatec.projeto2020.controller.filho;

import br.com.fatec.projeto2020.dao.FilhoDAO;
import br.com.fatec.projeto2020.dao.GenericDAO;
import br.com.fatec.projeto2020.dao.InternetDAO;
import br.com.fatec.projeto2020.dao.ProgramasDAO;
import br.com.fatec.projeto2020.dao.RelatorioDAO;
import br.com.fatec.projeto2020.model.Filho;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis Silva
 */
@WebServlet(name = "FilhoCarregar", urlPatterns = {"/FilhoCarregar"})
public class FilhoCarregar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=iso-8859-1");
        int idFilho = Integer.parseInt(request.getParameter("idFilho"));
        String mensagem = null;
        
        try{
            //carrega dados cliente
            GenericDAO oFilhoDAO = new FilhoDAO();
            Filho oFilho = (Filho) oFilhoDAO.carregar(idFilho);
            request.setAttribute("filho", oFilho);
            
             //Gera lista de programas
            GenericDAO oRelatorioDAO = new RelatorioDAO();
            request.setAttribute("relatorios", oRelatorioDAO.listar());           
            //dispacha objeto de lombada para a pagina jsp
            request.getRequestDispatcher("/cadastros/Filho/filhoCadastrar.jsp")
                    .forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FilhoCarregar.class.getName()).log(Level.SEVERE, null, ex);
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
