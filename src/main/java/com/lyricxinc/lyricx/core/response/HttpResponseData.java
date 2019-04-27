package com.lyricxinc.lyricx.core.response;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/*
Do not remove the getters nor change their access modifiers from public to any other type
 */
@Component
public class HttpResponseData {

  private LocalDateTime timestamp;
  private String message;
  private String details;

  public HttpResponseData() {
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }
}
