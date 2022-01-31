/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ejb.adminBean;
import entity.Groupmaster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "listServlet", urlPatterns = {"/listServlet"})
public class listServlet extends HttpServlet {

    @EJB
    adminBean bean;

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Collection<Groupmaster> group = bean.getAllGroup();
        PrintWriter out = res.getWriter();

        for (Groupmaster g : group) {
            out.println(g.getEmail());
        }

    }

}
