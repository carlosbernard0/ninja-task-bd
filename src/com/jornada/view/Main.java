// PACKAGES % IMPORTS
package com.jornada.view;

import com.jornada.entity.Caderno;
import com.jornada.entity.Tarefa;
import com.jornada.entity.Usuario;
import com.jornada.service.CadernoService;
import com.jornada.service.UsuarioService;
import com.jornada.service.TarefaService;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

// MAIN CLASS
public class Main {
    public static void main(String[] args) throws Exception {

        UsuarioService usuarioService = new UsuarioService();
        CadernoService cadernoService = new CadernoService();
        TarefaService tarefaService = new TarefaService();

        int idEscolhido;
        Scanner input = new Scanner(System.in);

        // MENU VIEW
        int firstMenuSelection = -1;
        do {
            System.out.println("*** SEJA BEM VINDO AO NINJA-TASK ***\n");

            // SELEÇÃO -> USUARIO (falta case 3)
            System.out.println(
                    """
                            -- LOGIN/CADASTRO USUARIO  --
                                                
                            -- (1) Criar Usuário
                            -- (2) Listar Usuarios
                            -- (3) Editar Usuarios
                            -- (4) Excluir Usuario
                            -- (5) Entrar no Caderno
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
                    usuario.setNome_usuario(input.nextLine());
//                    String registerUserName = input.nextLine();
//                    usuario.setNome_usuario(registerUserName);

                    System.out.print("-- Digite o email do usuário: ");
                    usuario.setEmail_usuario(input.nextLine());
//                    String registerUserEmail = input.nextLine();
//                    usuario.setEmail_usuario(registerUserEmail);

                    System.out.print("-- Digite a senha do usuário: ");
                    usuario.setSenha_usuario(input.nextLine());
//                    String registerUserPassword = input.nextLine();
//                    usuario.setSenha_usuario(registerUserPassword);

                    usuario.setData_registro(new Date());

                    try {
                        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
                        System.out.println("\n-- Usuário criado com sucesso | ID = #" + usuario.getId_usuario()); //Colocar o id
//                        System.out.println(usuario.getId_usuario());
//                        System.out.println("Usuario salvo!!");
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }

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


                    try {
                        boolean editado = usuarioService.editarUsuario(usuario);
                        if (editado) {
                            System.out.println("-- Usuario editado com sucesso!");
                        } else {
                            System.out.println("-- ERROR: Edição não finalizada ");
                        }

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 4 -> {
                    usuarioService.listar();
                    System.out.println("-- Qual id você deseja excluir?");
                    int idUsuario = Integer.parseInt(input.nextLine());

                    boolean excluido = usuarioService.excluirUsuario(idUsuario);
                    if (excluido) {
                        System.out.println("-- Usuario excluido com sucesso!");
                    } else {
                        System.out.println("-- ERROR: Exclusão não finalizada! ");
                    }
                }
                case 5 -> {
                    System.out.print("-- Digite o seu id: ");
                    idEscolhido = Integer.parseInt(input.nextLine());
                    byte selectListMenuCaderno;
                    do {

                        System.out.println("""
                                                    
                                -- MENU CADERNO --
                                                    
                                -- (1) Criar novo Caderno
                                -- (2) Listar Cadernos
                                -- (3) Apagar Caderno
                                -- (4) Tarefas do Caderno
                                -- (0) Sair do programa
                                                    
                                """);

                        System.out.print("-- Qual ação deseja realizar?: ");
                        selectListMenuCaderno = Byte.parseByte(input.nextLine());

                        switch (selectListMenuCaderno) {
                            case 0 -> System.exit(0);
                            case 1 -> {
                                try {//Criar Caderno
                                    Caderno caderno = new Caderno();
                                    Usuario usuario = new Usuario();

                                    System.out.println("-- Digite um nome para o caderno: ");
                                    caderno.setNomeCaderno(input.nextLine());

                                    usuario.setId_usuario(idEscolhido);
                                    caderno.setUsuario(usuario);

                                    cadernoService.salvarCaderno(caderno);
                                    System.out.println("-- Caderno criado com sucesso! | ID #" + caderno.getIdCaderno());

                                } catch (Exception e) {
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
                                if (excluido) {
                                    System.out.println("-- Caderno excluido com sucesso!");
                                } else {
                                    System.out.println("-- ERROR: Exclusão não finalizada! ");
                                }
                            }
                            case 4 -> {
                                System.out.print("-- Digite o id do caderno: ");
                                idEscolhido = Integer.parseInt(input.nextLine());
                                byte selectListMenuTarefa;
                                do {
                                    System.out.println("""
                                                                
                                            -- MENU TAREFA --
                                                                        
                                            -- (1) Criar nova Tarefa
                                            -- (2) Listar Tarefas
                                            -- (3) Editar Tarefa
                                            -- (4) Apagar Tarefa
                                            -- (0) Sair do programa
                                                                        
                                            """);

                                    System.out.print("-- Qual ação deseja realizar?: ");
                                    selectListMenuTarefa = Byte.parseByte(input.nextLine());

                                    switch (selectListMenuTarefa) {
                                        case 0 -> System.exit(0);
                                        case 1 -> {
                                            try {//Criar Tarefa
                                                Tarefa t = new Tarefa();
                                                Caderno caderno = new Caderno();

                                                System.out.print("-- Digite um nome para a tarefa: ");
                                                t.setNome(input.nextLine());

                                                System.out.print("-- Digite um status para a tarefa: ");
                                                t.setStatus(input.nextLine());

                                                caderno.setIdCaderno(idEscolhido);
                                                t.setCaderno(caderno);

                                                tarefaService.salvarTarefa(t);
                                                System.out.println("-- Tarefa criada com sucesso! | ID #" + t.getIdTarefa());

                                                caderno.setIdCaderno(idEscolhido);
                                                t.setCaderno(caderno);

                                            } catch (Exception e) {
                                                e.getMessage();

                                            }
                                        }
                                        case 2 ->{
                                            tarefaService.listarTarefas();
                                        }
                                        case 3 -> {

                                            tarefaService.listarTarefas();

                                            System.out.println("-- Qual id da tarefa você deseja fazer a alteraçã0?");
                                            int idTarefa = Integer.parseInt(input.nextLine());

                                            Tarefa tarefa = new Tarefa();
                                            Caderno caderno = new Caderno();
                                            tarefa.setIdTarefa(idTarefa);


                                            System.out.println("-- Digite um novo nome para a tarefa");
                                            tarefa.setNome(input.nextLine());

                                            System.out.println("-- Digite um novo status para a tarefa");
                                            tarefa.setStatus(input.nextLine());

                                            caderno.setIdCaderno(idEscolhido);
                                            tarefa.setCaderno(caderno);

                                            try {
                                                boolean editado = tarefaService.editarTarefa(tarefa);
                                                if (editado) {
                                                    System.out.println("-- Usuario editado com sucesso!");
                                                } else {
                                                    System.out.println("-- ERROR: Edição não finalizada ");
                                                }

                                            } catch (Exception e) {
                                                System.err.println(e.getMessage());
                                            }
                                        }
                                        case 4 ->{
                                            System.out.print("-- Qual id da tarefa você deseja excluir?");
                                            int idTarefa = Integer.parseInt(input.nextLine());

                                            boolean excluido = tarefaService.excluirTarefa(idTarefa);
                                            if (excluido) {
                                                System.out.println("-- Tarefa excluida com sucesso!");
                                            } else {
                                                System.out.println("-- ERROR: Exclusão não finalizada! ");
                                            }
                                        }
                                    }

                                } while (selectListMenuTarefa != 0);

                            }

                        }
                    } while (selectListMenuCaderno != 0);

                    System.out.println("""
                            -- (1) Continuar
                            -- (0) Sair
                            """);
                    firstMenuSelection = Integer.parseInt(input.nextLine());


                }


            }

        } while (firstMenuSelection != 0);
    }
}

