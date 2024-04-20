import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    String nomeUsuario;
    Usuario usuario;

    @BeforeEach
    public void setUp() {
        nomeUsuario = "Usuario";
        usuario = new Usuario(nomeUsuario);
    }

    @Test
    public void testUsuario() {
        assertEquals(nomeUsuario, usuario.getName());
    }

    @Test
    public void testCriarPost() {
        assertTrue(usuario.getPosts().isEmpty());
        String mensagem = "Test criar post";
        usuario.criarPost(mensagem);
        assertEquals(1, usuario.getPosts().size());
        assertTrue(usuario.getPosts().get(0).toString().contains(nomeUsuario + " -> " + mensagem));
    }

    @Test
    public void testSeguir() {
        Usuario outroUsuario = new Usuario("Outro usuario");
        String s = usuario.seguir(outroUsuario);
        assertEquals(1, usuario.getSeguindo().size());
        assertEquals("Usuario segue Outro usuario", s);
    }

    @Test
    public void testGetName() {
        assertEquals("Usuario", usuario.getName());
    }
}
