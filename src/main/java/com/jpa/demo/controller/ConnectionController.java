package com.jpa.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.demo.entity.Connection;
import com.jpa.demo.entity.service.ConnectionService;

@RestController
@RequestMapping("/api/connections")
public class ConnectionController {

	private final ConnectionService connectionService;

	@Autowired
	public ConnectionController(ConnectionService connectionService) {
		this.connectionService = connectionService;
	}

	@PostMapping
	public ResponseEntity<Connection> createConnection(@RequestBody Connection connection) {
		Connection createdConnection = connectionService.createConnection(connection.getUserId(),
				connection.getConnectionUserId(), connection.getStatus());
		return new ResponseEntity<>(createdConnection, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<Connection>> getConnectionById(@PathVariable Long id) {
		List<Connection> connection = connectionService.getConnectionsByUserId(id);
		if (connection != null) {
			return new ResponseEntity<>(connection, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Connection>> getConnectionsByUserId(@PathVariable Long userId) {
		List<Connection> connections = connectionService.getConnectionsByUserId(userId);
		return new ResponseEntity<>(connections, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Connection> updateConnectionStatus(@PathVariable Long id, @RequestBody String newStatus) {
		try {
			Connection updatedConnection = connectionService.updateConnectionStatus(id, newStatus);
			return new ResponseEntity<>(updatedConnection, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteConnection(@PathVariable Long id) {
		try {
			connectionService.deleteConnection(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
