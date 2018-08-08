package hse.holuhoev.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataBaseScheduler {
    private final DataBaseLoader dataBaseLoader;

    @Autowired
    public DataBaseScheduler(DataBaseLoader dataBaseLoader) {
        this.dataBaseLoader = dataBaseLoader;
    }

    // Run every day at 00:10 on server time
    @Scheduled(cron = "0 10 0 * * ?")
    private void loadDataBase() {
        dataBaseLoader.run("true","false","true");
    }
}
