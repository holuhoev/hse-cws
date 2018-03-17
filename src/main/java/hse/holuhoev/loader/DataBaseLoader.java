package hse.holuhoev.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 *
 * @author Evgeny Kholukhoev
 */
@Component
public class DataBaseLoader implements CommandLineRunner {
    private final DomainLoader domainLoader;

    @Autowired
    public DataBaseLoader(DomainLoader domainLoader) {
        this.domainLoader = domainLoader;
    }

    @Override
    public void run(String... strings) {
        domainLoader.run();

    }
}
