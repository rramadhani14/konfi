package dev.ramadhani.konfi.mongo;


import com.mongodb.client.MongoDatabase;
import lombok.NonNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class OrganizationAwareMongoDbFactory extends SimpleMongoClientDatabaseFactory {

    public OrganizationAwareMongoDbFactory(String connectionString) {
        super(connectionString);
    }

    @Override
    @NonNull
    public MongoDatabase getMongoDatabase() throws DataAccessException {
        String organizationId = OrganizationContext.getCurrentOrganization();
        if (organizationId == null) {
            throw new IllegalStateException("Organization id is null");
        }
        return super.getMongoClient().getDatabase(organizationId);
    }

    @Override
    @NonNull
    public MongoDatabase getMongoDatabase(@NonNull String dbName) throws DataAccessException {
        return super.getMongoClient().getDatabase(dbName);
    }
}

