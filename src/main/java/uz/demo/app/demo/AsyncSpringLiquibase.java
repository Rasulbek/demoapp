package uz.demo.app.demo;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class AsyncSpringLiquibase extends SpringLiquibase {

    private final Logger logger = LoggerFactory.getLogger(AsyncSpringLiquibase.class);

    @Override
    public void afterPropertiesSet() throws LiquibaseException {
        StopWatch watch = new StopWatch();
        watch.start();
        super.afterPropertiesSet();
        watch.stop();
        logger.debug("Started Liquibase in {} ms", watch.getTotalTimeMillis());
    }

}
