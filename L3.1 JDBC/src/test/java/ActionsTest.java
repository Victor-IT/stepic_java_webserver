import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ActionsTest {
    public void testAction() throws Exception {

//        String name = RandomStringUtils.randomAlphabetic(8);
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/v1/" + "users");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        System.out.println(httpResponse.getEntity().getContent());
        int result = httpResponse.getStatusLine().getStatusCode();

        ((CloseableHttpResponse) httpResponse).close();

        assertThat(result, equalTo(HttpStatus.SC_OK));
    }

}
