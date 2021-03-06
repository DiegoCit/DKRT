package br.edu.fapi.dkrt.dao.endereco.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.endereco.EnderecoDAO;
import br.edu.fapi.dkrt.model.endereco.EnderecoDTO;

import java.sql.*;

public class EnderecoDAOImpl implements EnderecoDAO {

    @Override
    public EnderecoDTO buscaEndereco(EnderecoDTO enderecoDTO, int ufId) {
        EnderecoDTO enderecoBusca = new EnderecoDTO();
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM enderecos WHERE endereco_rua = ?" +
                    " AND endereco_numero = ? AND endereco_cep = ?");

            preparedStatement.setString(1, enderecoDTO.getRua());
            preparedStatement.setString(2, enderecoDTO.getNumero());
            preparedStatement.setString(3, enderecoDTO.getCep());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                enderecoBusca.setId(resultSet.getInt("endereco_id"));
                enderecoBusca.setCep(resultSet.getString("endereco_cep"));
                enderecoBusca.setRua(resultSet.getString("endereco_rua"));
                enderecoBusca.setNumero(resultSet.getString("endereco_numero"));
                enderecoBusca.setComplemento(resultSet.getString("endereco_complemento"));
                enderecoBusca.setBairro(resultSet.getString("endereco_bairro"));
                enderecoBusca.setCidade(resultSet.getString("endereco_cidade"));
            } else {
                enderecoBusca.setId(criaEndereco(enderecoDTO, ufId));
                return enderecoBusca;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Falha na conexao");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return enderecoBusca;
    }

    private int criaEndereco(EnderecoDTO enderecoDTO, int ufId) throws SQLException, ClassNotFoundException {
        int id = 0;
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO enderecos (endereco_cep, endereco_rua, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade, uf_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, enderecoDTO.getCep());
            preparedStatement.setString(2, enderecoDTO.getRua());
            preparedStatement.setString(3, enderecoDTO.getNumero());
            preparedStatement.setString(4, enderecoDTO.getComplemento());
            preparedStatement.setString(5, enderecoDTO.getBairro());
            preparedStatement.setString(6, enderecoDTO.getCidade());
            preparedStatement.setInt(7, enderecoDTO.getUfDTO().getId());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        }
        return id;
    }
}
