package com.quanquan.qgodlibrary.okhttp.builder;

import com.quanquan.qgodlibrary.okhttp.request.PostStringRequest;
import com.quanquan.qgodlibrary.okhttp.request.RequestCall;

import okhttp3.MediaType;

/**
 * Created by qGod on 2017/4/21
 * Thank you for watching my code
 */
public class PostStringBuilder extends OkHttpRequestBuilder<PostStringBuilder>
{
    private String content;
    private MediaType mediaType;


    public PostStringBuilder content(String content)
    {
        this.content = content;
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PostStringRequest(url, tag, params, headers, content, mediaType).build();
    }


}
