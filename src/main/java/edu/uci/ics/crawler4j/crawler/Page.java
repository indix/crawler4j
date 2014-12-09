/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.uci.ics.crawler4j.crawler;

import edu.uci.ics.crawler4j.parser.ParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import edu.uci.ics.crawler4j.util.IO;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;

/**
 * This class contains the data for a fetched and parsed page.
 *
 * @author Yasser Ganjisaffar <lastname at gmail dot com>
 */
public class Page {

    /**
     * The URL of this page.
     * NOTE - This could most likely be updated to rel=canonical if present
     */
    protected WebURL url;

    /**
     * This will always be the URL of this page.
     */
    protected WebURL canonicalUrl;

    /**
     * The content of this page in binary format.
     */
    protected byte[] contentData;

    /**
     * The ContentType of this page.
     * For example: "text/html; charset=UTF-8"
     */
    protected String contentType;

    /**
     * The encoding of the content.
     * For example: "gzip"
     */
    protected String contentEncoding;

    /**
     * The charset of the content.
     * For example: "UTF-8"
     */
    protected String contentCharset;

    /**
     * Headers which were present in the response of the
     * fetch request
     */
    protected Header[] fetchResponseHeaders;

    /**
     * The parsed data populated by parsers
     */
    protected ParseData parseData;

    /**
     * Holds the content url of
     * &lt;meta http-equiv="refresh" content="0;URL=http://foo.bar/..." /&gt;
     */
    protected String metaRefresh;

    public Page(WebURL url) {
        this.url = url;
        this.canonicalUrl = url;
    }

    public WebURL getWebURL() {
        return url;
    }

    public WebURL getCanonicalUrl() {
        return canonicalUrl;
    }

    public void setWebURL(WebURL url) {
        this.url = url;
    }

    public void setCanonicalUrl(String canonicalUrl) {
        if (canonicalUrl != null && !canonicalUrl.isEmpty()) {
            WebURL canonicalWebUrl = new WebURL();
            canonicalWebUrl.setAnchor(this.url.getAnchor());
            canonicalWebUrl.setDepth(this.url.getDepth());
            canonicalWebUrl.setDocid(this.url.getDocid());
            canonicalWebUrl.setParentDocid(this.url.getParentDocid());
            canonicalWebUrl.setParentUrl(this.url.getParentUrl());
            canonicalWebUrl.setURL(canonicalUrl);
            this.canonicalUrl = canonicalWebUrl;
        }
    }

    /**
     * Loads the content of this page from a fetched
     * HttpEntity.
     */
    public void load(HttpEntity entity, int maxLength) throws Exception {

        contentType = null;
        Header type = entity.getContentType();
        if (type != null) {
            contentType = type.getValue();
        }

        contentEncoding = null;
        Header encoding = entity.getContentEncoding();
        if (encoding != null) {
            contentEncoding = encoding.getValue();
        }

        Charset charset = ContentType.getOrDefault(entity).getCharset();
        if (charset != null) {
            contentCharset = charset.displayName();
        }

        contentData = IO.toByteArray(entity, maxLength);
    }

    /**
     * Returns headers which were present in the response of the
     * fetch request
     */
    public Header[] getFetchResponseHeaders() {
        return fetchResponseHeaders;
    }

    public void setFetchResponseHeaders(Header[] headers) {
        fetchResponseHeaders = headers;
    }

    /**
     * Returns the parsed data generated for this page by parsers
     */
    public ParseData getParseData() {
        return parseData;
    }

    public void setParseData(ParseData parseData) {
        this.parseData = parseData;
    }

    /**
     * Returns the content of this page in binary format.
     */
    public byte[] getContentData() {
        return contentData;
    }

    public void setContentData(byte[] contentData) {
        this.contentData = contentData;
    }

    /**
     * Returns the ContentType of this page.
     * For example: "text/html; charset=UTF-8"
     */
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Returns the encoding of the content.
     * For example: "gzip"
     */
    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    /**
     * Returns the charset of the content.
     * For example: "UTF-8"
     */
    public String getContentCharset() {
        return contentCharset;
    }

    public void setContentCharset(String contentCharset) {
        this.contentCharset = contentCharset;
    }

    /**
     * Returns the URL from
     * &lt;meta http-equiv="refresh" content="0;URL=http://foo.bar/..." / &gt;
     */
    public String getMetaRefresh() {
        return metaRefresh;
    }

    public boolean hasMetaRefresh() {
        return metaRefresh != null && !metaRefresh.isEmpty();
    }

    public void setMetaRefresh(String metaRefresh) {
        this.metaRefresh = metaRefresh;
    }
}
