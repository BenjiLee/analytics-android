/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Segment.io, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.segment.android.models;

import java.util.Calendar;
import java.util.UUID;
import org.json.JSONObject;

public class BasePayload extends EasyJSONObject {

  private final static String TYPE_KEY = "type";
  private final static String CONTEXT_KEY = "context";
  private final static String ANONYMOUS_ID_KEY = "anonymousId";
  private final static String TIMESTAMP_KEY = "timestamp";
  private final static String INTEGRATIONS_KEY = "integrations";
  private final static String MESSAGE_ID_KEY = "messageId";

  public BasePayload(JSONObject obj) {
    super(obj);
  }

  public BasePayload(String type, Options options) {
    if (options == null) options = new Options();

    setType(type);
    setContext(options.getContext());
    setAnonymousId(options.getAnonymousId());
    setTimestamp(options.getTimestamp());
    setIntegrations(options.getIntegrations());
    setMessageId(UUID.randomUUID().toString());
  }

  public String getType() {
    return this.optString(TYPE_KEY, null);
  }

  public void setType(String type) {
    this.put(TYPE_KEY, type);
  }

  public EasyJSONObject getIntegrations() {
    JSONObject object = getObject(INTEGRATIONS_KEY);
    if (object == null) {
      return null;
    } else {
      return (EasyJSONObject) object;
    }
  }

  public void setIntegrations(EasyJSONObject integrations) {
    this.putObject(INTEGRATIONS_KEY, integrations);
  }

  public Context getContext() {
    JSONObject object = getObject(CONTEXT_KEY);
    if (object == null) {
      return null;
    } else {
      return (Context) object;
    }
  }

  public void setContext(Context context) {
    this.put(CONTEXT_KEY, context);
  }

  public String getAnonymousId() {
    return this.optString(ANONYMOUS_ID_KEY, null);
  }

  public void setAnonymousId(String anonymousId) {
    this.put(ANONYMOUS_ID_KEY, anonymousId);
  }

  public Calendar getTimestamp() {
    return getCalendar(TIMESTAMP_KEY);
  }

  public void setTimestamp(Calendar timestamp) {
    super.put(TIMESTAMP_KEY, timestamp);
  }

  public String getMessageId() {
    return this.optString(MESSAGE_ID_KEY, null);
  }

  public void setMessageId(String requestId) {
    this.put(MESSAGE_ID_KEY, requestId);
  }

  /**
   * Gets a simple string description of this payload.
   *
   * @return {String}
   */
  public String toDescription() {
    return String.format("%s [%s]", this.getType(), this.getMessageId());
  }
}