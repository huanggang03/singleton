/*
 * Copyright 2021 VMware, Inc.
 * SPDX-License-Identifier: EPL-2.0
 */

package formatting

import (
	v1 "sgtnserver/api/v1"
	"sgtnserver/internal/logger"

	"github.com/gin-gonic/gin"
)

type formattingRouter struct{}

func (r *formattingRouter) Init(e *gin.RouterGroup) {
	logger.Log.Debug("Initialize formatting router")

	e.GET("/date/localizedDate", GetLocalizedDate)
}
func init() {
	v1.Register(&formattingRouter{})
}