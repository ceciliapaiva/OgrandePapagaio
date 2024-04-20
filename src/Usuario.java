import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private String name;
    private List<String> posts;
    private List<String> seguindo;
    LocalDateTime data;

    // Constructor
    public Usuario(String name) {
        this.name = name;
        this.posts = new ArrayList<>();
        this.seguindo = new ArrayList<>();
    }

    // Métodos
    public void criarPost(String msg) {
        this.data = LocalDateTime.now();
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String msgFormatada = getName() + " -> " + msg + " (" + data.format(dataFormatada) + ")";
        this.posts.add(msgFormatada);
    }

    public String seguir(Usuario outroUsuario) {
        seguindo.add(outroUsuario.getName());
        return getName() + " segue " + outroUsuario.getName();
    }

    // getters
    public String getName() {
        return name;
    }

    public List<String> getPosts() {
        return posts;
    }

    public List<String> getSeguindo() {
        return seguindo;
    }

    public LocalDateTime getData() {
        return data;
    }

    // toString, equal and hashCode
    @Override
    public String toString() {
        return "Usuario{" +
                "name='" + name + '\'' +
                ", seguindo=" + seguindo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(name, usuario.name) && Objects.equals(posts, usuario.posts) && Objects.equals(seguindo, usuario.seguindo) && Objects.equals(data, usuario.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, posts, seguindo, data);
    }
}
