package steps.datatables;

import io.cucumber.java.DataTableType;
import ru.ifellow.api.model.RequestModel;

import java.util.Map;

public class DataTableTypeContainer {

    @DataTableType
    public RequestModel requestModel(Map<String, String> entry) {
        return new RequestModel(
                entry.get("method"),
                entry.get("body"),
                entry.get("path"),
                entry.get("url")
        );
    }
}