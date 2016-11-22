package com.joel.servlets;

import com.google.gson.Gson;
import com.joel.ClientInfo;
import com.joel.NetworkCtrl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tyrion on 10/9/2016.
 */

public class NetworkServlet extends HttpServlet {
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NetworkCtrl networkCtrl = new NetworkCtrl();
        networkCtrl.scan();
        List<ClientInfo> clientInfo = networkCtrl.getClientInfos();
        resp.getWriter().write(gson.toJson(clientInfo));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
