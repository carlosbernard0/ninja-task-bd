// PACKAGES % IMPORTS
package com.jornada.view;

import com.jornada.entity.Caderno;
import com.jornada.entity.Usuario;
import com.jornada.service.CadernoService;
import com.jornada.service.UsuarioService;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

// MAIN CLASS
public class Main {
    public static void main(String[] args) throws Exception {

        UsuarioService usuarioService = new UsuarioService();
        CadernoService cadernoService = new CadernoService();

        Integer idEscolhido;
        Scanner input = new Scanner(System.in);

        // MENU VIEW
        int firstMenuSelection = -1;
        do{
            System.out.println("*** SEJA BEM VINDO AO NINJA-TASK ***\n");

            // SELEÇÃO -> USUARIO (falta case 3)
            System.out.println(
                    """
                    -- LOGIN/CADASTRO USUARIO  --
                    
                    -- (1) Criar Usuário
                    -- (2) Listar Usuarios
                    -- (3) Editar Usuarios
                    -- (4) Excluir Usuario
                    -- (5) Entrar
                    -- (0) Sair do programa
                    
                    
                    """);

            System.out.print("-- Digite a opção desejada: ");
            byte userMenuSelection = Byte.parseByte(input.nextLine());

            switch (userMenuSelection) {
                case 0 -> System.exit(0);
                case 1 -> {
                    Usuario usuario = new Usuario();
                    System.out.println("\n-- CRIAÇÃO DE USUÁRIO --\n");
                    System.out.print("-- Digite o nome do usuário: ");
                    String registerUserName = input.nextLine();
                    usuario.setNome_usuario(registerUserName);

                    System.out.print("-- Digite o email do usuário: ");
                    String registerUserEmail = input.nextLine();
                    usuario.setEmail_usuario(registerUserEmail);

                    System.out.print("-- Digite a senha do usuário: ");
                    String registerUserPassword = input.nextLine();
                    usuario.setSenha_usuario(registerUserPassword);

                    usuario.setData_registro(new Date());

                    try{
                        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
                        System.out.println("\n-- Usuário criado com sucesso | ID = #" + usuario.getId_usuario()); //Colocar o id
//                        System.out.println("Usuario salvo!!");
//                        System.out.println(usuario.getId_usuario());
                    }catch (Exception e){
                        System.err.println(e.getMessage());
                    }

//
//                    System.out.println("\n-- Logado como " + registerUserName + "!");
                }
                case 2 -> {
                    usuarioService.listar();

                }
                case 3 -> {
                    usuarioService.listar();
                    System.out.println("-- Qual id você deseja fazer a alteraçã0?");
                    int idUsuario = Integer.parseInt(input.nextLine());

                    Usuario usuario = new Usuario();
                    usuario.setId_usuario(idUsuario);

                    System.out.println("-- Digite um novo nome para o usuario");
                    usuario.setNome_usuario(input.nextLine());

                    System.out.println("-- Digite um novo e-mail do usuario");
                    usuario.setEmail_usuario(input.nextLine());

                    System.out.println("-- Digite a nova senha do usuario");
                    usuario.setSenha_usuario(input.nextLine());

                    usuario.setData_registro(new Date());

                    try{
                        boolean editado = usuarioService.editarUsuario(usuario);
                        if (editado){
                            System.out.println("-- Usuario editado com sucesso!");
                        }else{
                            System.out.println("-- ERROR: Edição não finalizada ");
                        }

                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("-- Qual id você deseja excluir?");
                    int idUsuario = Integer.parseInt(input.nextLine());

                    boolean excluido = usuarioService.excluirUsuario(idUsuario);
                    if (excluido){
                            System.out.println("-- Usuario excluido com sucesso!");
                    }else{
                            System.out.println("-- ERROR: Exclusão não finalizada! ");
                    }
                }
                case 5->{
                    System.out.print("-- Digite o seu id: ");
                    idEscolhido = Integer.parseInt(input.nextLine());

                    System.out.println("""
                    
                    -- MENU CADERNO --
                    
                    -- (1) Criar novo Caderno
                    -- (2) Listar Cadernos
                    -- (3) Apagar Caderno
                    -- (4) Abrir Caderno
                    -- (0) Sair do programa
                    
                    """);

                    System.out.print("-- Qual ação deseja realizar?: ");
                    byte selectListMenuCaderno = Byte.parseByte(input.nextLine());

                    switch (selectListMenuCaderno) {
                        case 0 -> System.exit(0);
                        case 1 -> {
//                            System.out.print("\n-- Nome do Caderno: ");
//                            String tempListName = input.nextLine();
                            try{//Criar Caderno
                                Caderno c = new Caderno();

                                System.out.println("-- Digite um nome para o caderno: ");
                                c.setNomeCaderno(input.nextLine());

                                cadernoService.salvarCaderno(idEscolhido, c);
                                System.out.println("-- Caderno criado com sucesso! | ID #" + c.getIdCaderno());

                            }catch (Exception e){
                                e.getMessage();

                            }
                        }
                        case 2 -> {
                            cadernoService.listar();


                        }
                        case 3 -> {
                            System.out.print("-- Qual id do caderno você deseja excluir?");
                            int idCaderno = Integer.parseInt(input.nextLine());

                            boolean excluido = cadernoService.excluirCaderno(idCaderno);
                            if (excluido){
                                System.out.println("-- Usuario excluido com sucesso!");
                            }else{
                                System.out.println("-- ERROR: Exclusão não finalizada! ");
                            }
                        }
                        case 4 -> {
                            System.out.print("-- Digite o id do caderno: ");
                            idEscolhido = Integer.parseInt(input.nextLine());

                            System.out.println("""
                    
                            -- MENU TAREFA --
                            
                            -- (1) Criar nova Tarefa
                            -- (2) Listar Tarefas
                            -- (3) Editar Tarefa
                            -- (4) Apagar Tarefa
                            -- (0) Sair do programa
                            
                            """);

                            System.out.print("-- Qual ação deseja realizar?: ");
                            byte selectListMenuTarefa = Byte.parseByte(input.nextLine());

                            switch (selectListMenuTarefa) {
                                case 0 -> System.exit(0);
                                case 1 -> {
//                            System.out.print("\n-- Nome do Caderno: ");
//                            String tempListName = input.nextLine();
                                    try{//Criar Tarefa
                        }
                    }

                }


            }
            System.out.print("""
                    
                    -- OPÇÕES --
                        
                    -- (1) Continuar 
                    -- (0) Sair do programa
                        
                    """);
            firstMenuSelection = Integer.parseInt(input.nextLine());


            // VERIFICAR SENHA
            // GUARDAR EM "loginVerification" -> TRUE OU FALSE




        }while (firstMenuSelection != 0);

    }

}