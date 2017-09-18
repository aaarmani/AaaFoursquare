package andersonarmani.aaafoursquare.ui.fragment.pois;

import java.util.List;

import andersonarmani.aaafoursquare.api.model.Item;
import andersonarmani.aaafoursquare.ui.activity.ListPoiPresenter;

/**
 * Created by Armani andersonaramni@gmail.com on 18/09/2017.
 */

public interface PoisView {
    void initializeFragment(ListPoiPresenter listPoiPresenter);
    void setListItems(List<Item> itemList);
    void clear();
    void showError(String message);
}
