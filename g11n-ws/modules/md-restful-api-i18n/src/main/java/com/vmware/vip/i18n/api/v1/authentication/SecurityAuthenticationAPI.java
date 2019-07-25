/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: EPL-2.0
 */
package com.vmware.vip.i18n.api.v1.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vmware.vip.api.rest.API;
import com.vmware.vip.api.rest.APIOperation;
import com.vmware.vip.api.rest.APIParamName;
import com.vmware.vip.api.rest.APIParamValue;
import com.vmware.vip.api.rest.APIV1;
import com.vmware.vip.common.constants.ConstantsKeys;
import com.vmware.vip.common.i18n.dto.AuthResponseDTO;
import com.vmware.vip.common.i18n.dto.AuthenKeyDTO;
import com.vmware.vip.common.i18n.status.APIResponseStatus;
import com.vmware.vip.i18n.api.v1.utils.KeyService;
import com.vmware.vip.i18n.api.v1.utils.SignatureUtil;
import com.vmware.vip.i18n.api.v1.utils.TokenUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Provide RESTful API to authenticate the product’s identification before business API request.
 *
 */
@RestController
public class SecurityAuthenticationAPI {


    /**
     * Authenticate the request is legal by key.
     * <p>
     * If request is legal, vIP service generates token and session and return them to product.
     * <p>
     * if request is illegal,return Unauthorized to product.
     *
     * @param request
     *        Extends the ServletRequest interface to provide request information for HTTP servlets.
     * @param productName
     *        The name of product.
     * @param version
     *        The release version of product.
     * @param userID 
     *        User identifier randomly generated by product.
     * @param key
     *        Generated by a vIP staging server, and assigned to the product for authenticate identification.
     * @return APIResponseDTO 
     *         The object which represents response status.
     */
    @ApiOperation(value = APIOperation.Authenticate_VALUE, notes = APIOperation.Authenticate_NOTES)
    @RequestMapping(value = APIV1.AUTHENTICATION, method = RequestMethod.POST, produces = {
            API.API_CHARSET })
    @ResponseStatus(HttpStatus.OK)
    public AuthResponseDTO authentication(HttpServletRequest request,
            @ApiParam(name = APIParamName.PRODUCT_NAME, required = true, value = APIParamValue.PRODUCT_NAME) @RequestParam(value = APIParamName.PRODUCT_NAME, required = true) String productName,
            @ApiParam(name = APIParamName.VERSION, required = true, value = APIParamValue.VERSION) @RequestParam(value = APIParamName.VERSION, required = true) String version,
            @ApiParam(name = APIParamName.USER_ID, required = true, value = APIParamValue.USERID) @RequestParam(value = APIParamName.USER_ID, required = true) String userID,
            @ApiParam(name = APIParamName.KEY, required = true, value = APIParamValue.KEY) @RequestParam(value = APIParamName.KEY, required = true) String key) {
        AuthenKeyDTO keyDTO = new AuthenKeyDTO(productName, version, key, userID);
        AuthResponseDTO respDTO = new AuthResponseDTO();
        if (!KeyService.validateKey(keyDTO)) {
            respDTO.setResponse(APIResponseStatus.UNAUTHORIZED);
        } else {
            String token = TokenUtil.getToken(productName, version);
            HttpSession session = request.getSession();
            respDTO.setSessionID(session.getId());
            respDTO.setSignature(SignatureUtil.sign(session.getId(), token));
            respDTO.setToken(token);
            respDTO.setResponse(APIResponseStatus.OK);
            session.setAttribute(ConstantsKeys.TOKEN, respDTO.getToken());
            session.setAttribute(ConstantsKeys.SIGNATURE, respDTO.getSignature());
        }
        return respDTO;
    }

}
