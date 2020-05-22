/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.projeto2020.dao;

import br.com.fatec.projeto2020.model.Usuario;
import br.com.fatec.projeto2020.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Silva
 */
public class UsuarioDAO {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jeffersonpasserini
 */
    
    private Connection conexao;
    
    public UsuarioDAO() throws Exception{
        try{
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }   
    }
    
    public List<Usuario> listar(String loginUsuario)
    {
        List<Usuario> lstUsuario = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "select * from usuario u where u.login = ?;";
        
        try {
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1, loginUsuario);
           rs = stmt.executeQuery(); 
           while (rs.next()){
               Usuario oUsuario = new Usuario(rs.getInt("idlogin"),
                                    rs.getString("nome"),
                                    rs.getString("cpf"),
                                    rs.getString("login"),
                                    rs.getString("senha"),
                                    rs.getString("tipo"),
                                    rs.getInt("id"));
               lstUsuario.add(oUsuario);

           }           
        } catch (Exception ex)
        {
            System.out.println("Problemas ao listar usuario!"+
                    ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection (conexao, stmt,rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os "
                        + "parametros de conexão! Erro:"
                        +ex.getMessage());
            } 
        }
        
        return lstUsuario;
        
    }
    
    public Usuario logar(String login, String senha, String tipo)
    {
       
       PreparedStatement stmt = null;
       ResultSet rs = null;
       Usuario oUsuario = null;
       
       String sql ="select * from usuario u "
               + "where u.login=? and u.senha=? and u.tipo=?";
       try{
           
           stmt=conexao.prepareStatement(sql);
           stmt.setString(1, login);
           stmt.setString(2, senha);
           stmt.setString(3, tipo);
           rs=stmt.executeQuery();
           
           while (rs.next()){
                oUsuario = new Usuario(rs.getInt("idlogin"),
                                    rs.getString("nome"),
                                    rs.getString("cpf"),
                                    rs.getString("login"),
                                    rs.getString("senha"),
                                    rs.getString("tipo"),
                                    rs.getInt("id"));
           }

       }catch(Exception ex){
           System.out.println("Problemas ao carregar usuario!"+
                   "Erro:"+ex.getMessage());
       }finally{
           try{
               ConnectionFactory.closeConnection(conexao, stmt, rs);
           }
           catch (Exception ex){
               System.out.println("Problemas ao fechar os "
                       + "parâmetros de conexão!Erro:"
                       +ex.getMessage());
           }
       }
       return oUsuario; 
    }
    
    
}
