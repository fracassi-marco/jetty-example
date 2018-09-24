import net.sf.corn.httpclient.HttpForm;
import net.sf.corn.httpclient.HttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RouterTest {

    private ApplicationServer server = new ApplicationServer(4567, new MyApplication());

    @Before
    public void before() throws Exception {
        server.start();
    }

    @After
    public void after() throws Exception {
        server.stop();
    }

    @Test
    public void shouldCallPost() throws URISyntaxException, IOException {
        HttpForm form = new HttpForm(new URI("http://localhost:4567/add-name"));
        form.putFieldValue("name", "foo");
        HttpResponse response = form.doPost();

        assertThat(response.getData(), is("added"));
    }

    @Test
    public void shouldCallGet() throws Exception {
        putName("baz");
        putName("bar");

        HttpResponse response = new HttpForm(new URI("http://localhost:4567/list")).doGet();

        assertThat(response.getData(), is("baz,bar"));
    }

    @Test
    public void shouldBeEmpty() throws Exception {
        HttpForm form = new HttpForm(new URI("http://localhost:4567/list"));
        HttpResponse response = form.doGet();


        assertThat(response.getData(), is("empty"));
    }

    private void putName(String name) throws URISyntaxException, IOException {
        HttpForm form = new HttpForm(new URI("http://localhost:4567/add-name"));
        form.putFieldValue("name", name);
        form.doPost();
    }
}
