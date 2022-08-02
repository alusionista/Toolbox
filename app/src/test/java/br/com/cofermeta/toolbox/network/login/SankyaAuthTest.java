package br.com.cofermeta.toolbox.network.login;

import static org.junit.Assert.*;
import org.junit.Test;
import br.com.cofermeta.toolbox.data.model.Jsession;

public class SankyaAuthTest {
    @Test
    public void verifyLogin() throws InterruptedException {
        Jsession foo = new Jsession();
        new SankhyaAuth().verifyLogin("integracao", "654321", foo);
        Thread.sleep(5_000);
        assertEquals(foo.getUser(), "integracao");
        assertEquals(foo.getPassword(), "654321");
        assertEquals(foo.getStatus(), "1");
    }
}

