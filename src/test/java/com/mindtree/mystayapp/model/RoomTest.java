package com.mindtree.mystayapp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import static pl.pojo.tester.api.assertion.Method.CONSTRUCTOR;
import static pl.pojo.tester.api.assertion.Method.GETTER;
import static pl.pojo.tester.api.assertion.Method.SETTER;
import static pl.pojo.tester.api.assertion.Method.TO_STRING;

import org.junit.Before;
import org.junit.Test;

public class RoomTest {

	private Room room;
	private Room anotherRoom;

	@Before
	public void setUp() {
		room = new Room();
		anotherRoom = new Room();
	}

	@Test
	public void testPojoRoom() {

		assertPojoMethodsFor(Room.class).testing(GETTER).testing(SETTER).testing(TO_STRING).testing(CONSTRUCTOR)
				.areWellImplemented();
	}

	@Test
	public void equalsWhenEqual() {
		assertEquals(true, room.equals(anotherRoom));
	}

	@Test
	public void equalsWhenNotEqual() {
		room.setRoomId(1L);
		assertFalse(room.equals(anotherRoom));
	}

}
