import jakarta.servlet.http.HttpServlet;
import org.rythmengine.Rythm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RythmApplication extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws IOException {
        String name = request.getParameter("name");

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        if(name != null)
            write(response, Rythm.render("hello.html", name));
        else
            write(response, Rythm.render("warn.html", "Please provide the parameter name"));
    }

    private void write(HttpServletResponse resp, String message) throws IOException {
        resp.getWriter().write(message);
    }
}
