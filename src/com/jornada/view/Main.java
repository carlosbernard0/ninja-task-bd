// PACKAGES % IMPORTS
package com.jornada.view;

import com.jornada.entity.Usuario;
import com.jornada.service.UsuarioService;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

// MAIN CLASS
public class Main {
    public static void main(String[] args) throws Exception {

        UsuarioService usuarioService = new UsuarioService();
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
                    -- (2) Entrar em um usuário existente
                    -- (3) Apagar usuário existente
                    -- (0) Sair do programa
                    
                    """);

            System.out.print("-- Digite a opção desejada: ");
            byte userMenuSelection = Byte.parseByte(input.nextLine());

            switch (userMenuSelection) {
                case 0 -> System.exit(0);
                case 1 -> {
                    System.out.println("\n-- CRIAÇÃO DE USUÁRIO --\n");
                    System.out.print("-- Digite o nome do usuário: ");
                    String registerUserName = input.nextLine();
                    System.out.print("-- Digite o email do usuário: ");
                    String registerUserEmail = input.nextLine();
                    System.out.print("-- Digite a senha do usuário: ");
                    String registerUserPassword = input.nextLine();
                    System.out.println("\n-- Usuário criado com sucesso | ID = # "); //Colocar o id

                    System.out.println("\n-- Logado como " + registerUserName + "!");
                }
                case 2 -> {
                    // LOGIN

                    System.out.println("-- LOGIN --");

                    System.out.print("-- Id: ");
                    String tempLoginId = input.nextLine();
                    System.out.println("-- Senha: ");
                    String tempLoginPassword = input.nextLine();

//                   if(tempLoginPassword == SENHA)

                }

            }


            // VERIFICAR SENHA
            // GUARDAR EM "loginVerification" -> TRUE OU FALSE

            if(loginVerification){

                System.out.println("""
                        
                        -- MENU LISTA -- 
                        
                        -- (1) Criar nova lista
                        -- (2) Entrar em uma lista existente
                        -- (3) Mostrar listas 
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

            firstMenuSelection = Integer.parseInt(input.nextLine());

            switch (firstMenuSelection){
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

        }while (firstMenuSelection != 0);

    }

}