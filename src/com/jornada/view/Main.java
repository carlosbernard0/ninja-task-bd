// PACKAGES % IMPORTS
package com.jornada.view;

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

        boolean loginVerification = false;

        Scanner input = new Scanner(System.in);

        // MENU VIEW
        int firstMenuSelection = -1;
        do{
            System.out.println("*** SEJA BEM VINDO AO NINJA-TASK ***\n");

            // SELEÇÃO -> USUARIO (falta case 3)
            System.out.println(
                    """
                    -- MENU  --
                    
                    -- (1) Criar Usuário
                    -- (2) Listar Usuarios
                    -- (3) Editar Usuarios
                    -- (4) Excluir Usuario
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

            }


            // VERIFICAR SENHA
            // GUARDAR EM "loginVerification" -> TRUE OU FALSE

            if(loginVerification){

                System.out.println("""
                        
                        -- MENU -- 
                        
                        -- (1) Criar novo Caderno
                        -- (2) Entrar em uma lista existente
                        -- (3) Excluir Usuario
                        -- (4) Apagar usuário existente
                        -- (0) Sair do programa
                        
                        """);

                System.out.print("-- Qual ação deseja realizar?: ");
                byte selectListMenu = Byte.parseByte(input.nextLine());

                switch (selectListMenu) {
                    case 0 -> System.exit(0);
                    case 1 -> {
                        System.out.print("\n-- Nome da lista: ");
                        String tempListName = input.nextLine();

                        // CRIAR LISTA

                        System.out.print("\n-- Lista criada com sucesso!");
                    }
                    case 2 -> {
                        System.out.println("-- Digite o id da lista desejada: ");
                        byte tempListId = Byte.parseByte(input.nextLine());

                    }
                    case 3 -> {
                        // MOSTRAR NOME DE TODAS AS LISTAS + ID's
                    }
                }


            }
            System.out.print("""
                    
                    -- OPÇÕES --
                        
                    -- (1) Continuar 
                    -- (0) Sair do programa
                        
                    """);
            firstMenuSelection = Integer.parseInt(input.nextLine());


        }while (firstMenuSelection != 0);

    }

}