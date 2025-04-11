package com.jpa.demo.entity.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.demo.entity.Connection;
import com.jpa.demo.repository.ConnectionRepository;

@Service
public class ConnectionService {

    private final ConnectionRepository connectionRepository;

    @Autowired
    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public Connection createConnection(Long userId, Long connectionUserId, String status) {
        Connection connection = new Connection(null, userId, connectionUserId, LocalDateTime.now(), status);
        return connectionRepository.save(connection);
    }

    public List<Connection> getConnectionsByUserId(Long userId) {
        return connectionRepository.findByUserId(userId);
    }

    public List<Connection> getConnectionsByConnectionUserId(Long connectionUserId) {
        return connectionRepository.findByConnectionUserId(connectionUserId);
    }

    public Connection updateConnectionStatus(Long connectionId, String newStatus) {
        Connection connection = connectionRepository.findById(connectionId).orElseThrow(() -> new IllegalArgumentException("Connection not found"));
        connection.setStatus(newStatus);
        return connectionRepository.save(connection);
    }

    public void deleteConnection(Long connectionId) {
        connectionRepository.deleteById(connectionId);
    }
}
