package com.sinnau.sinnauApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  /**
   * React 애플리케이션의 모든 경로를 index.html로 포워딩하여 클라이언트 사이드 라우팅이 작동하도록 합니다.
   * 이렇게 하면 React Router가 브라우저에서 경로를 처리할 수 있습니다.
   */
  @GetMapping(value = {"/", "/{path:^(?!api|static|css|js|images).*$}/**"})
  public String forward() {
    return "forward:/index.html";
  }
}
