package checkout.handlers;

import checkout.CheckoutServer;
import checkout.MyDataSource;
import com.google.gson.Gson;
import org.webbitserver.HttpRequest;
import org.webbitserver.rest.Rest;

public class InMemoryBatchHandler implements JsonProcessor {
    private MyDataSource dataSource;

    public InMemoryBatchHandler(Gson json, MyDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public JsonProcessorResultWrapper execute(HttpRequest req) {
        Integer round = Integer.valueOf(Rest.param(req, "round"));

        dataSource.setStringData_FOR_TEST_ONLY(round, CheckoutServer.BATCH_LOCATION, req.body());

        return new JsonProcessorResultWrapper(201, "Cached batch changed");
    }
}
