package com.mindtree.mystayapp.util;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.mindtree.mystayapp.util.DateUtil;

public class DateUtilTest {

	private Date currentDate;
	@SuppressWarnings("unused")
	private DateUtil util;

	@Before
	public void setUp() {
		util = new DateUtil();
		currentDate = new Date(System.currentTimeMillis());
	}

	@Test
	public void testAddDays() {
		Date newDate = DateUtil.addDays(currentDate, 0);
		assertEquals(currentDate, newDate);
	}

}
