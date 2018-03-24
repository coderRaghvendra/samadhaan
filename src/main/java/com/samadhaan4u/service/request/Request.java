package com.samadhaan4u.service.request;

import com.samadhaan4u.service.response.Response;

/**
 * Created by raghvendra.mishra on 03/02/18.
 */
public interface Request {

    Response process();

    long getUserId();

    void setUserId(long userId);
}
