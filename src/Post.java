import java.util.ArrayList;
import java.util.List;

public class Post {
    public List<String> mural;
    public List<Usuario> listaDeUsuarios;

    // constructor
    public Post() {
        this.mural = new ArrayList<>();
        this.listaDeUsuarios = new ArrayList<>();
    }

    // metódos
    public String criarUsuario(String nome) throws Exception {
        for (Usuario a : listaDeUsuarios) {
            if (a.getName().equalsIgnoreCase(nome)) {
                throw new Exception("Este nome já existe");
            }
        }
        Usuario novoUsuario = new Usuario(nome);
        listaDeUsuarios.add(novoUsuario);
        return nome + " seja bem-vinde ao GRANDE PAPAGAIO 🦜";
    }

    public String fazerPostagem(Usuario usuario, String mensagem) throws Exception{
        if (!listaDeUsuarios.contains(usuario)){
            throw new Exception("Este usuário não pode postar, pois é inexistente");
        }

        usuario.criarPost(mensagem);
        mural.add(usuario.getName() + " -> " + mensagem);
        return "Post realizado com sucesso no mural de " + usuario.getName();
    }

    public String seguirUsuario(Usuario usuario, Usuario outroUsuario) throws Exception{
        if (!listaDeUsuarios.contains(outroUsuario)){
            throw new Exception("Não é possivel seguir um usuário inexistente");
        }
        usuario.seguir(outroUsuario);
        return usuario.getName() + " segue " + outroUsuario.getName();
    }

    public List<String> verMural(Usuario usuario) {
        List<String> muralDoUsuario = new ArrayList<>();

        for (String post : usuario.getPosts()){
            muralDoUsuario.add(post);
        }

        for (String seguido : usuario.getSeguindo()){
            for (Usuario ativos : listaDeUsuarios){
                if (seguido.equalsIgnoreCase(ativos.getName())){
                    muralDoUsuario.addAll(ativos.getPosts());
                }
            }
        }
        return muralDoUsuario;
    }

    public Usuario encontraUsuario(String nome) {
        for (Usuario usuario : listaDeUsuarios) {
            if (usuario.getName().equals(nome)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Mural{" +
                "mural=" + mural +
                '}';
    }
}
