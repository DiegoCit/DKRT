package br.edu.fapi.dkrt.dao.produto.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

import java.sql.*;

public class ProdutoDAOImpl implements ProdutoDAO {

    @Override
    public int createProduto(ProdutoDTO produtoDTO) {
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto (produto_nome, produto_descricao, produto_preco, produto_qtdEstoque, " +
                    "produto_dataCadastroProduto) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, produtoDTO.getNome());
            preparedStatement.setString(2, produtoDTO.getDescricao());
            preparedStatement.setDouble(3, produtoDTO.getPreco());
            preparedStatement.setInt(4, produtoDTO.getQuantidade());
            preparedStatement.setDate(5, new java.sql.Date(produtoDTO.getDataCadastroProduto().getTime()));

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int idProduto = resultSet.getInt(1);
                return idProduto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
