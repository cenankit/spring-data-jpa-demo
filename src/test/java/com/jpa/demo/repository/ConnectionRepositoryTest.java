package com.jpa.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jpa.demo.entity.Connection;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConnectionRepositoryTest {

    @Autowired
    ConnectionRepository connectionRepository;
    Connection connection;

    @BeforeEach
    void setup() {
        connection = new Connection(1L, 12L, 2L, LocalDateTime.now(), "pending"); // Align connectionUserId with test
        connectionRepository.save(connection);
    }

    @Test
    void test_findByConnectionUserId() {
        List<Connection> connList = connectionRepository.findByConnectionUserId(2L);
        assertThat(connList.get(0).getConnectionUserId()).isEqualTo(2L); // Assert correctly
    }

    @AfterEach
    void tearDown() {
        connectionRepository.deleteAll();
    }
}
