package org.example;


import org.jdbcdriver.SkyflowDriver;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SkyflowDriverTest {

	@Test
	public void driver_works_in_normal_case() throws SQLException {
		String resourceName = "test01.csv";

		ClassLoader classLoader = getClass().getClassLoader();
		String path = classLoader.getResource(resourceName).getPath();

		String parent = Paths.get(path).getParent().toAbsolutePath().toString();

		Driver driver = new SkyflowDriver();

		try(
				Connection con = driver.connect("jdbc:Skyflow:"+parent, null);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM test01.csv;")
		) {
			assertTrue(rs.next());
			assertEquals("A", rs.getString(1));
			assertEquals("B", rs.getString(2));

			assertTrue(rs.next());
			assertEquals("C", rs.getString(1));
			assertEquals("D", rs.getString(2));

			assertFalse(rs.next());
		};
	}

	@Test
	public void driver_returns_with_null_for_not_Skyflow_url() throws SQLException {
		assertNull(new SkyflowDriver().connect("jdbc:xxx", null));
	}
}