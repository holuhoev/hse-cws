package hse.holuhoev.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * @author Evgeny Kholukhoev
 */
@Component
public class DataBaseLoader implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(DataBaseLoader.class);
    private final DomainLoader domainLoader;
    private final WorkloadLoader workloadLoader;

    @Autowired
    public DataBaseLoader(DomainLoader domainLoader, WorkloadLoader workloadLoader) {
        this.domainLoader = domainLoader;
        this.workloadLoader = workloadLoader;
    }

    @Override
    public void run(String... strings) {
        logger.info("Run database loader with params: {}", Arrays.toString(strings));
        domainLoader.run(strings);
        workloadLoader.run(strings);
    }
}
