package com.htf.zdh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.po.AppInfoList;
import com.htf.zdh.jdbc.po.HtfLinkInfo;
import com.htf.zdh.service.HtfLinkInfoService;
import com.htf.zdh.service.bo.AppInfoListBo;
import com.htf.zdh.service.bo.HtfLinkInfoBo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "htf链接列表" })
@RestController("link")
@RequestMapping(value = "link")
public class LinkController {

	private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

	@Autowired
	private HtfLinkInfoService htfLinkInfoService;

	@ApiOperation(value = "链接列表", notes = "传id只查1条，不传id查所有，分页默认查第1页前10条")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示的数量", required = false, paramType = "query", dataType = "int") })
	@RequestMapping(value = "/all", method = { RequestMethod.GET })
	@ResponseBody
	public Result getLinkList(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize) {
		Result result = new Result();
		result.setCode(1);
		result.setMessage("查询成功");
		if (id != null && !id.toString().equals("")) {
			HtfLinkInfo link = new HtfLinkInfo();
			link = htfLinkInfoService.selectByPrimaryKey(id);
			result.setData(link);
			return result;
		}
		HtfLinkInfoBo links = htfLinkInfoService.selectAll(pageNum, pageSize);
		result.setData(links);
		return result;
	}

	@ApiOperation(value = "新增或者更新", notes = "传id是更新，不传id是新增")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", required = false, paramType = "form", dataType = "int"),
			@ApiImplicitParam(name = "functionType", value = "分组", required = false, paramType = "form", dataType = "string"),
			@ApiImplicitParam(name = "functionName", value = "名称", required = true, paramType = "form", dataType = "string"),
			@ApiImplicitParam(name = "linkDetails", value = "详情", required = true, paramType = "form", dataType = "string"),
			@ApiImplicitParam(name = "remark", value = "备注", required = false, paramType = "form", dataType = "string") })
	@RequestMapping(value = "/insert", method = { RequestMethod.POST })
	@ResponseBody
	public Result insert(@RequestParam(required = false) Integer id,
			@RequestParam(value = "functionName", required = true) String functionName,
			@RequestParam(value = "linkDetails", required = true) String linkDetails,
			@RequestParam(value = "functionType", required = false) String functionType,
			@RequestParam(value = "remark", required = false) String remark) {
		Result result = new Result();
		result.setCode(1);
		result.setMessage("操作成功");
		HtfLinkInfo record = new HtfLinkInfo();
		record.setId(id);
		record.setFunctionType(functionType);
		record.setFunctionName(functionName);
		record.setLinkDetails(linkDetails);
		record.setRemark(remark);
		int i = htfLinkInfoService.update(record);

		if (i != 1) {
			result.setCode(500);
			result.setMessage("操作失败");
		}

		return result;

	}

}
