package com.yy.demo.web.controller;

import javax.annotation.Resource;
import javax.management.RuntimeMBeanException;

import com.yy.demo.web.anno.AttrbuteArg;
import com.yy.demo.vo.ResponseResult;
import com.yy.demo.web.anno.Login;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.yy.demo.bean.User;
import com.yy.demo.service.IUserService;

import java.io.FileNotFoundException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;

    @ApiOperation(value="home目录", httpMethod = "GET", produces = "application/json")
    @RequestMapping("/home")
	public ResponseResult home() {
		log.info("home");
		return ResponseResult.ok("Hello World!");
	}

	@ApiOperation(value = "添加user", httpMethod = "POST", notes = "notes",
            produces = "application/json", consumes = "application/json")
	@PostMapping("/insert")
	public ResponseResult insert(@RequestBody User vo) {
        log.info("insert param vo:{}", vo);

        int id = userService.insert(vo);
		log.info("id:{}", id);
		return ResponseResult.ok();
	}

    /**
     * eq: http://localhost:2371/user/findById?id=3
     * @param id
     * @return
     */
	@GetMapping("/findById")
    @ApiOperation(value = "添加user", httpMethod = "GET", produces = "application/json")
    @Login
	public ResponseResult findById(long id, @AttrbuteArg("test") String name, ModelMap modelMap) {
        log.info("findById param id:{}", id);

        User user = userService.findById(id);
		log.info("user:{}", user);
        System.out.println("ll: "+modelMap.get("ll"));
		return ResponseResult.ok(user);
	}

	/**
	 * http://localhost:8000/user/updateSchoolName?schoolName=vl&studentId=3
	 *
	 * @param schoolName
	 * @param studentId
	 * @return
	 */
    @GetMapping("/updateSchoolName")
	//@Transactional(rollbackFor = Exception.class)
    public ResponseResult updateSchoolName(String schoolName, long studentId) throws FileNotFoundException {
        log.info("updateSchoolName param schoolName:{} studentId:{}", schoolName, studentId);

        userService.updateSchoolName(schoolName, studentId);

        //int i = 10/0;
        return ResponseResult.ok();
    }


	public static void main(String[] args) {
		try{
			output();
		}catch (Exception e){
			log.error("test 场景 ERROR:",e);
		}
	}

	private static void output() {
		out1();
	}

	private static void out1() {
		out2();
	}
	private static void out2() {
		out();
	}
	private static void out() {
		int i = 10/0;
	}
}