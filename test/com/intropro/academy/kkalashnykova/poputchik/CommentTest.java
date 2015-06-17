package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommentTest {

	@Test
	public void testSmoke() {
		Comment comment = new Comment();
		assertNotNull(comment);
	}
}
