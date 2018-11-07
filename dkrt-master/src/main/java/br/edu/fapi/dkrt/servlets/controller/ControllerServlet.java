package br.edu.fapi.dkrt.servlets.controller;

import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        if ("login".equalsIgnoreCase(acao)) {
            String usuario = req.getParameter("usuario");
            String senha = req.getParameter("senha");

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setEmail(usuario);
            usuarioDTO.setSenha(senha);
            req.getSession().setAttribute("usuario", usuarioDTO);
            req.getRequestDispatcher("login").forward(req, resp);
        }

        if ("excluir".equalsIgnoreCase(acao)){
            req.getRequestDispatcher("/excluir").forward(req, resp);
        }

        if ("cadastro".equalsIgnoreCase(acao)){
            String tipo = req.getParameter("tipo");
            if ("cliente".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("cadastro?tipo=cliente").forward(req, resp);
            } else if ("produto".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("cadastro?tipo=produto").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if ("cadastro".equalsIgnoreCase(acao)){
            String tipo = req.getParameter("tipo");
            if ("produto".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("cadastro?tipo=produto").forward(req, resp);
            } else if ("cliente".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("cadastro?tipo=cliente").forward(req, resp);
            }
        }

        if ("vendas".equalsIgnoreCase(acao)){
            String tipo = req.getParameter("tipo");
            if ("efetuar".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("venda?tipo=efetuar").forward(req, resp);
            } else if ("cancelar".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("venda?tipo=cancelar").forward(req, resp);
            }
        }

        if ("orcamento".equalsIgnoreCase(acao)){
            String tipo = req.getParameter("tipo");
            if ("gerar".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("orcamento?tipo=gerar").forward(req, resp);
            } else if ("salvos".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("orcamento?tipo=salvos").forward(req, resp);
            }
        }

        if ("relatorio".equalsIgnoreCase(acao)){
            String tipo = req.getParameter("tipo");
            if ("venda".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("relatorio?tipo=venda").forward(req, resp);
            } else if ("estoque".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("relatorio?tipo=estoque").forward(req, resp);
            }
        }

        if ("pesquisa".equalsIgnoreCase(acao)){
            String tipo = req.getParameter("tipo");
            if ("cliente".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("pesquisa?tipo=cliente").forward(req, resp);
            } else if ("produto".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("pesquisa?tipo=produto").forward(req, resp);
            }
        }
    }
}