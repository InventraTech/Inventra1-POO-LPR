package org.inventra.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.inventra.dao.ItemPreListaDAO;
import org.inventra.model.ItemPreListaMolde;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ItemPreListaServlet", value = "/itemprelista")
public class ItemPreListaServlet extends HttpServlet {

    private ItemPreListaDAO itemPreListaDAO;

    @Override
    public void init() {
        itemPreListaDAO = new ItemPreListaDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        List<ItemPreListaMolde> itensprelista = itemPreListaDAO.listarItensPreLista();

        request.setAttribute("itensprelista", itensprelista);

        request.getRequestDispatcher(
                "/WEB-INF/views/lista-itemprelista.jsp"
        ).forward(request, response);
    }


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");

        if (acao == null){
            acao = "";
        }

        if(acao.equals("excluir")) {
            int id = Integer.parseInt(request.getParameter("id"));

            itemPreListaDAO.deletarItemPreLista(id);

            response.sendRedirect(
                    request.getContextPath() + "/itemprelista"
            );
            return;
        }

        else if(acao.equals("atualizar_coluna")){
            int id = Integer.parseInt(request.getParameter("id"));
            String coluna = request.getParameter("coluna");
            String novoValor = request.getParameter("novoValor");

            itemPreListaDAO.atualizarColunaItemPreLista(coluna, novoValor, id);

            response.sendRedirect(request.getContextPath() + "/itemprelista");

            //Return faz com que pare aqui caso seja escolhido
            return;

        }

        else if (acao.equals("atualizar_completo")) {
            int id = Integer.parseInt(request.getParameter("id_fornecedor"));

            int fkPreLista =
                    Integer.parseInt(request.getParameter("fkPreLista"));
            int fk_fornecedor =
                    Integer.parseInt(request.getParameter("fk_fornecedor"));
            int fk_produto =
                    Integer.parseInt(request.getParameter("fk_prduto"));
            int qtd =
                    Integer.parseInt(request.getParameter("quantidade"));


            ItemPreListaMolde atualizarItemPreLista = new ItemPreListaMolde(fkPreLista, fk_fornecedor, fk_fornecedor, qtd);

            itemPreListaDAO.atualizarItemPreListaCompleto(atualizarItemPreLista, id);

            response.sendRedirect(
                    request.getContextPath() + "/itemprelista"
            );

            return;
        }

        int fkPreLista =
                Integer.parseInt(request.getParameter("fkPreLista"));
        int fk_fornecedor =
                Integer.parseInt(request.getParameter("fk_fornecedor"));
        int fk_produto =
                Integer.parseInt(request.getParameter("fk_prduto"));
        int qtd =
                Integer.parseInt(request.getParameter("quantidade"));


        ItemPreListaMolde novoItemPreLista = new ItemPreListaMolde(fkPreLista, fk_fornecedor, fk_fornecedor, qtd);

        itemPreListaDAO.inserirItemPreLista(novoItemPreLista);

        response.sendRedirect(
                request.getContextPath() + "/itemprelista"
        );
    }
}

