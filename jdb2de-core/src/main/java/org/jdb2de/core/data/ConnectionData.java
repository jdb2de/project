package org.jdb2de.core.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *
 * @author  Rodrigo Tavares
 */
@Component
@Scope(value = "singleton")
public class ConnectionData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1229916425332251970L;

    /**
     * Driver class
     */
    private String driver;

    /**
     * Database connection url
     */
    private String url;

    /**
     * Database user name
     */
    private String userName;

    /**
     * Database password
     */
    private String password;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(driver, url, userName, password);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("driver", driver)
                .add("url", url)
                .add("userName", userName)
                .add("password", password)
                .toString();
    }
}
