package com.thegrid.controllers;

import com.google.api.server.spi.config.Api;
import com.thegrid.Constants;

@Api(
        name = "partidosmanager",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public abstract class ApiController {
}
