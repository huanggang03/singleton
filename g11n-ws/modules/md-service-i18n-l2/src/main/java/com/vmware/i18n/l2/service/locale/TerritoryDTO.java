/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: EPL-2.0
 */
package com.vmware.i18n.l2.service.locale;

import java.io.Serializable;
import java.util.Map;

public class TerritoryDTO implements Serializable {

	private static final long serialVersionUID = 5025165753700602227L;

	private String language;
	
	private String defaultRegionCode;

	private Map<String, String> territories;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDefaultRegionCode() {
		return defaultRegionCode;
	}

	public void setDefaultRegionCode(String defaultRegionCode) {
		this.defaultRegionCode = defaultRegionCode;
	}

	public Map<String, String> getTerritories() {
		return territories;
	}

	public void setTerritories(Map<String, String> territories) {
		this.territories = territories;
	}

}
