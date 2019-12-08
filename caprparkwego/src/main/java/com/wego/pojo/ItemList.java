package com.wego.pojo;

        import org.codehaus.jackson.annotate.JsonProperty;

        import java.util.List;

public class ItemList {
    @JsonProperty("items")
    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
