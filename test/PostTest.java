import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    private static Post mural;
    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;

    @BeforeEach
    public void setUp() {
        mural = new Post();
        usuario1 = new Usuario("usuario1");
        usuario2 = new Usuario("usuario2");
        usuario3 = new Usuario("usuario3");
    }

    @Test
    public void testCriarUsuario() throws Exception {
        assertEquals("usuario1 seja bem-vinde ao GRANDE PAPAGAIO 🦜", mural.criarUsuario("usuario1"));
        assertNotNull(mural.encontraUsuario("usuario1"));
    }
    @Test
    public void testExceptionCriarUsuario() throws Exception{
        try {
            mural.criarUsuario("usuario1");
        }catch (Exception e){
            assertEquals("Este nome já existe", e.getMessage());
        }
    }

    @Test
    public void testFazerPostagem() throws Exception {
        mural.criarUsuario(usuario1.getName());
        mural.encontraUsuario("usuario1");
        mural.fazerPostagem(usuario1, "Teste");
        assertEquals(1, usuario1.getPosts().size());
    }
    @Test
    public void testExceptionFazerPostagem() throws Exception{
        Usuario usuarioInexistente = new Usuario("usuario inexistente");
        try {
            mural.fazerPostagem(usuarioInexistente, "Tentando fazer postagem com usuario inexistente");
        }catch (Exception e){
            assertEquals("Este usuário não pode postar, pois é inexistente", e.getMessage());
        }
    }

    @Test
    public void testSeguiUsuario() throws Exception {
        mural.criarUsuario(usuario2.getName());
        mural.criarUsuario(usuario1.getName());

        usuario2 = mural.encontraUsuario("usuario2");
        usuario1 = mural.encontraUsuario("usuario1");

        mural.seguirUsuario(usuario1, usuario2);

        assertEquals(1, usuario1.getSeguindo().size());
    }
    @Test
    public void testExceptionSeguirUsuario() throws Exception{
        mural.encontraUsuario(usuario1.getName());
        try {
            String s = mural.seguirUsuario(usuario1, usuario3);
        } catch (Exception e) {
            assertEquals("Não é possivel seguir um usuário inexistente", e.getMessage());
        }
    }

    @Test
    public void testVerMural() throws Exception {
        String usuarioNome = "usuario";
        String usuario1Nome = "usuario1";
        String usuario2Nome = "usuario2";
        String postagem1 = "Postagem de usuario1";
        String postagem2 = "Postagem de usuario2";

        Post mural = new Post();
        Usuario usuario = new Usuario(usuarioNome);
        mural.criarUsuario(usuarioNome);

        mural.fazerPostagem(usuario, "teste");

        mural.criarUsuario(usuario1Nome);
        mural.fazerPostagem(mural.encontraUsuario(usuario1Nome), postagem1);

        mural.criarUsuario(usuario2Nome);
        mural.fazerPostagem(mural.encontraUsuario(usuario2Nome), postagem2);

        mural.seguirUsuario(usuario, mural.encontraUsuario(usuario2Nome));

        List<String> muralUsuario = mural.verMural(usuario);

        assertEquals(2, muralUsuario.size()); // Tamanho correto: 2 posts
        assertFalse(muralUsuario.toString().contains(postagem1)); // Postagem do usuario1
        assertTrue(muralUsuario.toString().contains(postagem2)); // Postagem do usuario2
    }
}