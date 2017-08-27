package org.jdb2de.core.data;

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
    public String toString() {
        return super.toString();
    }
}
