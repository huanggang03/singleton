/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: EPL-2.0
 */
package com.vmware.vip.i18n.api.v1.translation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.vmware.vip.BootApplication;
import com.vmware.vip.i18n.api.v1.common.CacheUtil;
import com.vmware.vip.i18n.api.v1.common.ConstantsForTest;
import com.vmware.vip.i18n.api.v1.common.RequestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class TranslationProductComponentKeyAPITest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void authentication() throws Exception {
		String authenticationResult = RequestUtil.sendRequest(
				webApplicationContext, ConstantsForTest.POST,
				ConstantsForTest.AuthenticationAPIURI);
		CacheUtil.cacheSessionAndToken(webApplicationContext,
				authenticationResult);
	}

	@Test
	public void testStringBaseTranslation() throws Exception {
		RequestUtil.sendRequest(webApplicationContext, ConstantsForTest.GET,
				ConstantsForTest.StringTranslationAPIURI);
	}
}
