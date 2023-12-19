package br.com.bancoagro.contabancariaservice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.logging.Logger;

@ActiveProfiles(value = "test")
public class ContaCorrenteServiceTest {

    Logger LOGGER = (Logger) LoggerFactory.getLogger(ContaCorrenteServiceTest.class);
    @Test
    public void testaGetAll(){
        LOGGER.info("Teste GET ALL");
   }
    @Test
    public void testaGetById(){
        LOGGER.info("Teste GET BY ID");
    }
    @AfterEach
    public void tearDown(){
        LOGGER.info("After Each");
    }
}
