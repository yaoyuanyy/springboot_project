package com.yy.demo.web.controller;

import javax.annotation.Resource;

import com.yy.demo.web.anno.AttrbuteArg;
import com.yy.demo.vo.ResponseResult;
import com.yy.demo.web.anno.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yy.demo.bean.User;
import com.yy.demo.service.IUserService;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;

	@RequestMapping("/home")
	public ResponseResult home() {
		log.info("home");
		return ResponseResult.ok("Hello World!");
	}

	@RequestMapping("/insert")
	public ResponseResult insert(User vo) {
		int id = userService.insert(vo);
		log.info("id:{}", id);
		return ResponseResult.ok();
	}

    /**
     *
     * @param id
     * @return
     */
	@RequestMapping("/findById")
    @Login
	public ResponseResult findById(long id, @AttrbuteArg("test") String name, ModelMap modelMap) {
		User user = userService.fingById(id);
		log.info("user:{}", user);
        System.out.println("ll: "+modelMap.get("ll"));
		return ResponseResult.ok(user);
	}

    @RequestMapping("/updateSchoolName")
    public ResponseResult updateSchoolName(String schoolName, long studentId) {

        userService.updateSchoolName(schoolName, studentId);
        return ResponseResult.ok();
    }
}