package dataAccessLayer;

// Java Imports
import java.sql.SQLException;
import javax.sql.DataSource;

// Custom Imports
import configuration.DBConf;
import utility.ConfFileParser;

/**
 * The GameDB class configures the database connection by using information
 * parsed from the configuration file.
 */
public class GameDB {

    private DBConf configuration;
    private DataSource dataSource;

    /**
     * Configures the database connection.
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public GameDB() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // Load the configuration file into memory
        configure();
        // Create a connection to the database
        String connectURI = "jdbc:mysql://" + configuration.getDBURL() + "/"
                + configuration.getDBName() + "?" + "user=" + configuration.getDBUsername() + "&password=" + configuration.getDBPassword();
        dataSource = ConnectionPool.setupDataSource(connectURI);
    }

    /**
     * Parses the database configuration file and store those values into
     * memory.
     */
    private void configure() {
        // Prepare for database info storage
        configuration = new DBConf();
        // Parse the configuration file
        ConfFileParser confFileParser = new ConfFileParser("conf/db.conf");
        configuration.setConfRecords(confFileParser.parse());
    }

    /**
     * Gets the data source for database access.
     *
     * @return the data source
     */
    public DataSource getDataSource() {
        return dataSource;
    }
}