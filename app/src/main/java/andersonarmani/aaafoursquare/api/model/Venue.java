
package andersonarmani.aaafoursquare.api.model;

import java.lang.*;
import java.lang.Object;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("canonicalUrl")
    @Expose
    private String canonicalUrl;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("likes")
    @Expose
    private Likes likes;
    @SerializedName("dislike")
    @Expose
    private Boolean dislike;
    @SerializedName("ok")
    @Expose
    private Boolean ok;

    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("ratingColor")
    @Expose
    private String ratingColor;
    @SerializedName("ratingSignals")
    @Expose
    private Integer ratingSignals;
    @SerializedName("allowMenuUrlEdit")
    @Expose
    private Boolean allowMenuUrlEdit;
    @SerializedName("beenHere")
    @Expose
    private BeenHere beenHere;
    @SerializedName("specials")
    @Expose
    private Specials specials;
    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("reasons")
    @Expose
    private Reasons reasons;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("page")
    @Expose
    private Page page;
    @SerializedName("storeId")
    @Expose
    private String storeId;
    @SerializedName("hasMenu")
    @Expose
    private Boolean hasMenu;
    @SerializedName("menu")
    @Expose
    private Menu menu;
    @SerializedName("venuePage")
    @Expose
    private VenuePage venuePage;
    @SerializedName("hereNow")
    @Expose
    private HereNow hereNow;
    @SerializedName("createdAt")
    @Expose
    private Integer createdAt;
    @SerializedName("tips")
    @Expose
    private Tips_ tips;
    @SerializedName("tags")
    @Expose
    private List<java.lang.Object> tags = null;
    @SerializedName("shortUrl")
    @Expose
    private String shortUrl;
    @SerializedName("timeZone")
    @Expose
    private String timeZone;
    @SerializedName("listed")
    @Expose
    private Listed listed;
    @SerializedName("phrases")
    @Expose
    private List<Phrase> phrases = null;
    @SerializedName("hours")
    @Expose
    private Hours hours;
    @SerializedName("popular")
    @Expose
    private Popular popular;
    @SerializedName("pageUpdates")
    @Expose
    private PageUpdates pageUpdates;
    @SerializedName("inbox")
    @Expose
    private Inbox inbox;
    @SerializedName("venueChains")
    @Expose
    private List<java.lang.Object> venueChains = null;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("bestPhoto")
    @Expose
    private BestPhoto bestPhoto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRatingSignals() {
        return ratingSignals;
    }

    public void setRatingSignals(Integer ratingSignals) {
        this.ratingSignals = ratingSignals;
    }

    public Boolean getAllowMenuUrlEdit() {
        return allowMenuUrlEdit;
    }

    public void setAllowMenuUrlEdit(Boolean allowMenuUrlEdit) {
        this.allowMenuUrlEdit = allowMenuUrlEdit;
    }

    public BeenHere getBeenHere() {
        return beenHere;
    }

    public void setBeenHere(BeenHere beenHere) {
        this.beenHere = beenHere;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public HereNow getHereNow() {
        return hereNow;
    }

    public void setHereNow(HereNow hereNow) {
        this.hereNow = hereNow;
    }

    public Boolean getHasMenu() {
        return hasMenu;
    }

    public void setHasMenu(Boolean hasMenu) {
        this.hasMenu = hasMenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public VenuePage getVenuePage() {
        return venuePage;
    }

    public void setVenuePage(VenuePage venuePage) {
        this.venuePage = venuePage;
    }

    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Boolean getDislike() {
        return dislike;
    }

    public void setDislike(Boolean dislike) {
        this.dislike = dislike;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public Specials getSpecials() {
        return specials;
    }

    public void setSpecials(Specials specials) {
        this.specials = specials;
    }

    public Reasons getReasons() {
        return reasons;
    }

    public void setReasons(Reasons reasons) {
        this.reasons = reasons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Tips_ getTips() {
        return tips;
    }

    public void setTips(Tips_ tips) {
        this.tips = tips;
    }

    public List<java.lang.Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Listed getListed() {
        return listed;
    }

    public void setListed(Listed listed) {
        this.listed = listed;
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
    }

    public Popular getPopular() {
        return popular;
    }

    public void setPopular(Popular popular) {
        this.popular = popular;
    }

    public PageUpdates getPageUpdates() {
        return pageUpdates;
    }

    public void setPageUpdates(PageUpdates pageUpdates) {
        this.pageUpdates = pageUpdates;
    }

    public Inbox getInbox() {
        return inbox;
    }

    public void setInbox(Inbox inbox) {
        this.inbox = inbox;
    }

    public List<Object> getVenueChains() {
        return venueChains;
    }

    public void setVenueChains(List<Object> venueChains) {
        this.venueChains = venueChains;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public BestPhoto getBestPhoto() {
        return bestPhoto;
    }

    public void setBestPhoto(BestPhoto bestPhoto) {
        this.bestPhoto = bestPhoto;
    }
}
