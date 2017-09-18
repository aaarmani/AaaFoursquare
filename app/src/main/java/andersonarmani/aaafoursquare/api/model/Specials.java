
package andersonarmani.aaafoursquare.api.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specials {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("items")
    @Expose
    private List<java.lang.Object> items = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<java.lang.Object> getItems() {
        return items;
    }

    public void setItems(List<java.lang.Object> items) {
        this.items = items;
    }

}
