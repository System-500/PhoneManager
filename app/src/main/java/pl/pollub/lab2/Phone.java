package pl.pollub.lab2;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
//alt + insert
@Entity(tableName = "phones")
public class Phone {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long id;

    @ColumnInfo(name = "manufacturer")
    private String manufacturer;


    @ColumnInfo(name = "model")
    private String model;

    @ColumnInfo(name = "android_version")
    private String androidVersion;

    @ColumnInfo(name = "web_site")
    private String webSite;

    public Phone(String manufacturer, String model, String androidVersion, String webSite) {

        this.manufacturer = manufacturer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.webSite = webSite;
    }
@Ignore
    public Phone(Long id, String manufacturer, String model, String androidVersion, String webSite) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.webSite = webSite;
    }


    public Long getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}
