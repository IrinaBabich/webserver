import com.babich.webserver.Server;
import org.junit.Test;

import java.io.IOException;


public class ServerITest {
    @Test
    public void testServer() throws IOException {
        Server server = new Server();
        server.setPort(3000);
        server.setWebAppPath("src/main/resources/webapp");
        server.start();
    }
}


// localhost: 3000/index.html
// localhost:3000/style/styles.css

//src/main/resources/webapp/index.html -> send as response with status 200 OK
//src/main/resources/webapp/style/styles.css -> send as response with status 200 OK

//localhost:3000/unknownResource -> 404 not found
// day stranitsy -> Bad request