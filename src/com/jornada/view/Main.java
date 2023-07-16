package com.jornada.view;

import com.jornada.entity.Usuario;
import com.jornada.service.UsuarioService;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        UsuarioService usuarioService = new UsuarioService();

        Scanner input = new Scanner(System.in);

        int opcao = -1;
        while (opcao != 0){
            System.out.println("***Seja Bem Vindo ao Ninja Task***");
            System.out.println("""
                O que deseja fazer:
                1 - Criar Usuario
                2 - Entrar em um usuario existente
                3 - Sair do programa""");
//            System.out.println("Digite 1 para criar Usuario");
//            System.out.println("Digite 2 para listar Usuario");
//            System.out.println("Digite 3 para editar Usuario");
//            System.out.println("Digite 4 para excluir Usuario");
            opcao = Integer.parseInt(input.nextLine());

            input.nextLine();
            switch (opcao){
                case 1 -> {
                    Usuario usuario = new Usuario();
                    System.out.println("Digite o nome do usuario");
                    usuario.setNome_usuario(input.nextLine());

                    System.out.println("Digite o e-mail do usuario");
                    usuario.setEmail_usuario(input.nextLine());

                    System.out.println("Digite o senha do usuario");
                    usuario.setSenha_usuario(input.nextLine());

                    usuario.setData_registro(new Date());

                    try{
                        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
                        System.out.println("Usuario salvo!!");
                    }catch (Exception e){
                        System.err.println(e.getMessage());
                    }

                }
                case 2 ->{
                    usuarioService.listar();
                }
                case 3 -> {
                    usuarioService.listar();
                    System.out.println("Qual id você deseja fazer a alteraçã0?");
                    int idUsuario = Integer.parseInt(input.nextLine());

                    Usuario usuario = new Usuario();
                    usuario.setId_usuario(idUsuario);

                    System.out.println("Digite um novo nome para o usuario");
                    usuario.setNome_usuario(input.nextLine());

                    System.out.println("Digite um novo e-mail do usuario");
                    usuario.setEmail_usuario(input.nextLine());

                    System.out.println("Digite a nova senha do usuario");
                    usuario.setSenha_usuario(input.nextLine());

                    usuario.setData_registro(new Date());

                    try{
                       boolean editado = usuarioService.editarUsuario(usuario);
                        System.out.println("Edição do usuario: " + editado);

                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }

                }

                case 4 -> {
                    usuarioService.listar();
                    System.out.println("Qual id você deseja excluir?");
                    int idUsuario = Integer.parseInt(input.nextLine());

                    boolean excluido =usuarioService.excluirUsuario(idUsuario);
                    System.out.println("Excluido = " + excluido);

                }
            }
        }
    }
}
