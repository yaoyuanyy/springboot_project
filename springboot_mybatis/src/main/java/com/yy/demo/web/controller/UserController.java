package com.yy.demo.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yy.demo.web.anno.AttrbuteArg;
import com.yy.demo.vo.ResponseResult;
import com.yy.demo.web.anno.Login;
import com.yy.demo.web.anno.RequestParamJson;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.yy.demo.bean.User;
import com.yy.demo.service.IUserService;

@RestController
@Slf4j
@RequestMapping("/v1/user")
public class UserController {

	@Resource
	private IUserService userService;

    @ApiOperation(value="home目录", httpMethod = "GET", produces = "application/json")
    @RequestMapping("/home")
	public ResponseResult home() {
		log.info("home");
		return ResponseResult.ok("Hello World!");
	}

    /**
     *
     * <pre>
     * curl -X POST -H 'Content-Type:application/json' -d '{"name":"<script>alert(11)</script>", "mobile":"", "studentId":23, "score":20}' http://localhost:8000/v1/user/insert
     *
     * 运行此例子，你会发现js代码存到了数据库中，等取出在页面显示的时候会弹出alert提示框，从而存在xss攻击问题
     * 所以，要加一个xssFilter拦截器，具体逻辑看代码
     * </pre>
     * @param vo
     * @return
     */
	@ApiOperation(value = "添加user", httpMethod = "POST", notes = "notes",
            produces = "application/json", consumes = "application/json")
	@PostMapping("/insert")
	public ResponseResult insert(@RequestBody User vo, HttpServletRequest request) {

	    log.info(" getParammterValues:{}", request.getParameterNames());
	    log.info("getParameterMap:{}", request.getParameterMap());

        log.info("insert param vo:{}", vo);

        int id = userService.insert(vo);
		log.info("id:{}", id);
		return ResponseResult.ok();
	}

    /**
     * eq: http://localhost:8000/v1/user/findById?id=3
     * @param id
     * @return
     */
	@GetMapping("/findById")
    @ApiOperation(value = "添加user", httpMethod = "GET", produces = "application/json")
    //@Login
	public ResponseResult findById(long id, @AttrbuteArg("test") String name, ModelMap modelMap) {
        log.info("findById param id:{}", id);

        User user = userService.fingById(id);
		log.info("user:{}", user);
        System.out.println("ll: "+modelMap.get("ll"));
		return ResponseResult.ok(user);
	}

    @GetMapping("/updateSchoolName")
    public ResponseResult updateSchoolName(String schoolName, long studentId) {
        log.info("updateSchoolName param schoolName:{} studentId:{}", schoolName, studentId);

        userService.updateSchoolName(schoolName, studentId);
        return ResponseResult.ok();
    }

    @PostMapping("/updateName")
    public ResponseResult updateName(@RequestParamJson(name = "name", required = false) String tmpName, long studentId) {
        log.info("updateName param name:{} studentId:{}", tmpName, studentId);

        userService.updateSchoolName(tmpName, studentId);
        return ResponseResult.ok();
    }
}