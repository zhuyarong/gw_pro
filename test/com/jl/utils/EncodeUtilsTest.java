package com.jl.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(value = JUnit4.class)
public class EncodeUtilsTest {
	@Test
	public void testMd5(){
		System.out.println(EncodeUtils.md5Encode("123456"));
	}
}
