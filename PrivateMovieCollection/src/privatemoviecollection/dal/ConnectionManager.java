/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 * @ Class for constructing a connector we can use when we want to connect to
 * our DB throughout our application.
 * @author pmj
 */
public class ConnectionManager {

    private final SQLServerDataSource ds = new SQLServerDataSource();

    /**
     * Connection constructor with connector parameters.
     */
    public ConnectionManager() {
        ds.setDatabaseName("PrivateMovieCollection");
        ds.setUser("CS2017A_28");
        ds.setPassword("Ellap060174");
        ds.setServerName("EASV-DB2");
        ds.setPortNumber(1433);
    }

    /**
     * @return our connection
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException {
        return ds.getConnection();
    }
}
