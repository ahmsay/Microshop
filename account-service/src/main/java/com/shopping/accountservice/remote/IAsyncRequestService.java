package com.shopping.accountservice.remote;

public interface IAsyncRequestService {

    IAsyncRequest createRequest();

    <T> T sendRequest(IAsyncRequest remotingRequest);
}