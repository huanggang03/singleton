/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: EPL-2.0
 */
package com.vmware.vip.common.cache;

import org.apache.commons.lang3.StringUtils;

import com.vmware.vip.common.constants.ConstantsChar;
import com.vmware.vip.common.i18n.dto.MultiComponentsDTO;
import com.vmware.vip.common.i18n.dto.SingleComponentDTO;

/**
 * The keys used in cache are generated by this class.
 * 
 */
public class CachedKeyGetter {

    /**
     * Get a string to use as key for cached object, the cached object is the object instance of
     * MultiComponentsDTO.
     *
     * @param baseTranslationDTO a DTO object of MultiComponentsDTO
     * 
     * @return the key as string, e.g. com.vmware.vrb.admin.7.0.1.fr,zh_CN
     */
    public static String getMultiComponentsCachedKey(MultiComponentsDTO multiComponentsDTO) {
        StringBuilder key = new StringBuilder();
        if (!StringUtils.isEmpty(multiComponentsDTO.getProductName())) {
            key.append(multiComponentsDTO.getProductName());
        }
        if (multiComponentsDTO.getComponents() != null && multiComponentsDTO.getComponents().size() > 0) {
            key.append(ConstantsChar.DOT).append(multiComponentsDTO.getComponents());
        }
        if (!StringUtils.isEmpty(multiComponentsDTO.getVersion())) {
            key.append(ConstantsChar.DOT).append(multiComponentsDTO.getVersion());
        }
        if (multiComponentsDTO.getLocales() !=null && multiComponentsDTO.getLocales().size() > 0) {
            key.append(ConstantsChar.DOT).append(multiComponentsDTO.getLocales());
            key.append(ConstantsChar.DOT).append(multiComponentsDTO.getPseudo());
        }
        return key.toString();
    }

    /**
     * Get a string to use as key for cached object, the cached object is the object instance of
     * SingleComponentDTO.
     *
     * @param baseComponentMessagesDTO a DTO object of SingleComponentDTO
     * 
     * @return the key as string, e.g. com.vmware.vrb.admin.fr
     */
    public static String getOneCompnentCachedKey(SingleComponentDTO singleComponentDTO) {
        StringBuilder key = new StringBuilder();
        if (!StringUtils.isEmpty(singleComponentDTO.getProductName())) {
            key.append(singleComponentDTO.getProductName());
        }
        if (!StringUtils.isEmpty(singleComponentDTO.getVersion())) {
            key.append(ConstantsChar.DOT).append(singleComponentDTO.getVersion());
        }
        if (!StringUtils.isEmpty(singleComponentDTO.getComponent())) {
            key.append(ConstantsChar.DOT).append(singleComponentDTO.getComponent());
        }
        if (!StringUtils.isEmpty(singleComponentDTO.getLocale())) {
            key.append(ConstantsChar.DOT).append(singleComponentDTO.getLocale());
        }
        return key.toString();
    }
}
