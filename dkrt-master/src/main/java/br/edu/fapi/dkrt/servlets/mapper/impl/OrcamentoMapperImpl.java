package br.edu.fapi.dkrt.servlets.mapper.impl;

import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.servlets.mapper.BaseMapper;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrcamentoMapperImpl implements BaseMapper<HttpServletRequest, OrcamentoDTO> {

    @Override
    public OrcamentoDTO doMap(HttpServletRequest req){
        OrcamentoDTO orcamentoDTO = (OrcamentoDTO) req.getSession().getAttribute("orcamentoBusca");
        String desconto = req.getParameter("descontoOrcamento");
        String dataExpiracao = req.getParameter("dataExpiracao");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        orcamentoDTO.setDesconto(Integer.parseInt(desconto));
        float valorDesconto = (orcamentoDTO.getValorTotal()*orcamentoDTO.getDesconto())/100;
        orcamentoDTO.setValorTotal(orcamentoDTO.getValorTotal()-valorDesconto);
        if (dataExpiracao != null) {
            try {
                orcamentoDTO.setDataExpiracao(dateFormat.parse(dataExpiracao));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return orcamentoDTO;
    }
}
