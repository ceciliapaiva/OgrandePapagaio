import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Post mural = new Post();
        Usuario usuario;
        Scanner sc = new Scanner(System.in);

        while (true){
            menu();
            int opc = sc.nextInt();
            sc.nextLine();

            switch (opc){
                case 1:
                    System.out.println("Nome de usúario: ");
                    String novoUsuario = sc.nextLine();
                    try {
                        String msg = mural.criarUsuario(novoUsuario);
                        System.out.println(msg);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                break;
                case 2:
                    System.out.println("Seu nome de usuário: ");
                    String nomeA = sc.nextLine();
                    usuario = mural.encontraUsuario(nomeA);

                    if (usuario != null){
                        System.out.println("Mensagem a ser postada: ");
                        String msg = sc.nextLine();
                        try {
                            String retorno = mural.fazerPostagem(usuario, msg);
                            System.out.println(retorno);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }else {
                        System.out.println("Usuário não encontrado");
                    }
                break;
                case 3:
                    System.out.println("Seu nome de usuário: ");
                    String nomeB = sc.nextLine();
                    usuario = mural.encontraUsuario(nomeB);

                    System.out.println("Nome do usuário a seguir: ");
                    String name = sc.nextLine();
                    Usuario outroUsuario = mural.encontraUsuario(name);
                    try {
                       String retornoo = mural.seguirUsuario(usuario, outroUsuario);
                        System.out.println(retornoo);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                break;
                case 4:
                    System.out.println("Seu nome de usuário: ");
                    String nomeC = sc.nextLine();
                    usuario = mural.encontraUsuario(nomeC);
                    if (nomeC != null){
                        System.out.println("Mural " + usuario.getName());
                        List<String> muralUsuario = mural.verMural(usuario);
                        for (String post : muralUsuario){
                            System.out.println(post);
                        }
                    }else {
                        System.out.println("Usuário não encontrado");
                    }
                break;
                case 0:
                    System.out.println("Até mais o(*￣▽￣*)ブ");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }


        }

    }
    public static void menu() {
        System.out.println("\nO GRANDE PAPAGAIO");
        System.out.println("1. Criar usuário");
        System.out.println("2. Fazer postagem");
        System.out.println("3. Seguir usuário");
        System.out.println("4. Vizualizar mural");
        System.out.println("0. Sair");
        System.out.print("Escolha uma das opção: ");
    }
}